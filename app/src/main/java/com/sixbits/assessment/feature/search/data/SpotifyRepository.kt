package com.sixbits.assessment.feature.search.data

import android.util.Log
import androidx.annotation.NonNull
import com.sixbits.assessment.feature.search.datasource.local.SearchLocalDataSource
import com.sixbits.assessment.feature.search.datasource.network.SpotifyRemoteDataSource
import com.sixbits.assessment.feature.search.domain.datamodel.ArtistDetailsDataModel
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel
import com.sixbits.assessment.feature.search.domain.datamodel.TrackDetailsDataModel
import com.sixbits.assessment.feature.search.domain.failures.UnknownFailure
import com.sixbits.extention.Failure
import io.reactivex.rxjava3.core.Single
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

class SpotifyRepository @Inject constructor(
    private val spotifyService: SpotifyRemoteDataSource,
    private val cache: SearchLocalDataSource
) {
    fun search(query: String, token: String): Single<List<SpotifyDataModel>> {
        return requestCall(spotifyService.search(query, token))
    }

    fun getCached(): Single<List<SpotifyDataModel>> {
        return cache.getCachedRequests()
    }

    fun getArtistDetails(id: String, token: String): Single<ArtistDetailsDataModel> {
        return requestCall(spotifyService.getArtistDetails(id, token))
            .flatMap {
                cache.saveCache(it).andThen(Single.just(it))
            }
    }

    fun getTrackDetails(id: String, token: String): Single<TrackDetailsDataModel> {
        return requestCall(spotifyService.getTrackDetails(id, token))
            .flatMap {
                cache.saveCache(it).andThen(Single.just(it))
            }
    }

    @NonNull
    private fun <T : Any> requestCall(@NonNull request: Single<T>): Single<T> {
        return request.onErrorResumeNext { err: Throwable? ->
            if (err is HttpException) {
                Log.d(TAG, "requestCall: ${err.response()?.body()}")
                if (err.code() == HttpURLConnection.HTTP_FORBIDDEN ||
                    err.code() == HttpURLConnection.HTTP_UNAUTHORIZED
                ) {
                    return@onErrorResumeNext Single.error(Failure.UnauthorizedError)
                }
                if (err.code() >= HttpURLConnection.HTTP_MULT_CHOICE &&
                    err.code() < HttpURLConnection.HTTP_INTERNAL_ERROR
                ) {
                    // A request error, should be fixed from the backend or as an update to the app
                    // Meaning it should be reported from here as a bug!
                    return@onErrorResumeNext Single.error(UnknownError(err.message()))
                }
                if (err.code() == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                    return@onErrorResumeNext Single.error(Failure.ServerError)
                }
            }
            Single.error(UnknownFailure("Unknown Error"))
        }
    }

    companion object {
        private const val TAG = "SpotifyRepository"
    }
}
