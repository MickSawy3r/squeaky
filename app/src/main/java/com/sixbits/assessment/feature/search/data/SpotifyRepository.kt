package com.sixbits.assessment.feature.search.data

import android.util.Log
import androidx.annotation.NonNull
import com.sixbits.assessment.core.interactor.Either
import com.sixbits.assessment.core.interactor.onSuccessAsync
import com.sixbits.assessment.feature.search.datasource.local.SearchLocalDataSource
import com.sixbits.assessment.feature.search.datasource.network.ArtistDetailsResponse
import com.sixbits.assessment.feature.search.datasource.network.ArtistDetailsResponseMapper
import com.sixbits.assessment.feature.search.datasource.network.SearchResponse
import com.sixbits.assessment.feature.search.datasource.network.SearchResponseMapper
import com.sixbits.assessment.feature.search.datasource.network.SpotifyRemoteDataSource
import com.sixbits.assessment.feature.search.datasource.network.TrackDetailsResponse
import com.sixbits.assessment.feature.search.datasource.network.TrackDetailsResponseMapper
import com.sixbits.assessment.feature.search.domain.datamodel.ArtistDetailsDataModel
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel
import com.sixbits.assessment.feature.search.domain.datamodel.TrackDetailsDataModel
import com.sixbits.assessment.feature.search.domain.failures.UnknownFailure
import com.sixbits.extention.Failure
import retrofit2.Call
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

class SpotifyRepository @Inject constructor(
    private val spotifyService: SpotifyRemoteDataSource,
    private val cache: SearchLocalDataSource,
    private val artistDetailsResponseMapper: ArtistDetailsResponseMapper,
    private val searchListMapper: SearchResponseMapper,
    private val trackDetailsResponseMapper: TrackDetailsResponseMapper
) {
    fun search(query: String, token: String): Either<Failure, List<SpotifyDataModel>> {
        return requestCall(
            spotifyService.search(query, token),
            {
                searchListMapper.map(it)
            },
            SearchResponse()
        )
    }

    suspend fun getCached(): Either<Failure, List<SpotifyDataModel>> {
        return Either.Right(cache.getCachedRequests())
    }

    suspend fun getArtistDetails(
        id: String,
        token: String
    ): Either<Failure, ArtistDetailsDataModel> {
        val result = requestCall(
            spotifyService.getArtistDetails(id, token),
            { artistDetailsResponseMapper.map(it) },
            ArtistDetailsResponse()
        )

        result.onSuccessAsync {
            cache.saveCache(it)
        }

        return result
    }

    suspend fun getTrackDetails(id: String, token: String): Either<Failure, TrackDetailsDataModel> {
        val result = requestCall(
            spotifyService.getTrackDetails(id, token),
            { trackDetailsResponseMapper.map(it) },
            TrackDetailsResponse()
        )

        result.onSuccessAsync {
            cache.saveCache(it)
        }

        return result
    }

    @NonNull
    private fun <T, R> requestCall(
        @NonNull request: Call<T>,
        transform: (T) -> R,
        defaultValue: T
    ): Either<Failure, R> {
        val response = request.execute()
        if (response.isSuccessful) {
            return Either.Right(transform(response.body() ?: defaultValue))
        } else {
            if (response.errorBody() is HttpException) {
                val err = response.body() as HttpException
                Log.d(TAG, "requestCall: ${err.response()?.body()}")
                if (err.code() == HttpURLConnection.HTTP_FORBIDDEN ||
                    err.code() == HttpURLConnection.HTTP_UNAUTHORIZED
                ) {
                    return Either.Left(Failure.UnauthorizedError)
                }
                if (err.code() >= HttpURLConnection.HTTP_MULT_CHOICE &&
                    err.code() < HttpURLConnection.HTTP_INTERNAL_ERROR
                ) {
                    // A request error, should be fixed from the backend or as an update to the app
                    // Meaning it should be reported from here as a bug!
                    return Either.Left(UnknownFailure(err.message()))
                }
                if (err.code() == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                    return Either.Left(Failure.ServerError)
                }
            }

            return Either.Left(UnknownFailure("Unknown Error"))
        }
    }

    companion object {
        private const val TAG = "SpotifyRepository"
    }
}
