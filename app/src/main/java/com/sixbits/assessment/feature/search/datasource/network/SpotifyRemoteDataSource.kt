package com.sixbits.assessment.feature.search.datasource.network

import retrofit2.Call
import javax.inject.Inject

class SpotifyRemoteDataSource @Inject constructor(
    private val iSpotifyApi: ISpotifyApi
) {

    suspend fun search(query: String, authToken: String): Call<SearchResponse> {
        return iSpotifyApi.searchSpotify(
            query = query,
            authToken = authToken
        )
    }

    suspend fun getArtistDetails(id: String, authToken: String): Call<ArtistDetailsResponse> {
        return iSpotifyApi.getArtistDetails(
            id = id,
            authToken = authToken
        )
    }

    suspend fun getTrackDetails(id: String, authToken: String): Call<TrackDetailsResponse> {
        return iSpotifyApi.getTrackDetails(
            id = id,
            authToken = authToken
        )
    }
}
