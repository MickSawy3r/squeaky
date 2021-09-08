package com.sixbits.assessment.feature.search.datasource.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ISpotifyApi {
    @GET("v1/search")
    suspend fun searchSpotify(
        @Query("q") query: String,
        @Query("type") type: String = "track,artist",
        @Header("Authorization") authToken: String
    ): Call<SearchResponse>

    @GET("v1/artists/{id}")
    suspend fun getArtistDetails(
        @Path("id") id: String,
        @Header("Authorization") authToken: String
    ): Call<ArtistDetailsResponse>

    @GET("v1/tracks/{id}")
    suspend fun getTrackDetails(
        @Path("id") id: String,
        @Header("Authorization") authToken: String
    ): Call<TrackDetailsResponse>
}
