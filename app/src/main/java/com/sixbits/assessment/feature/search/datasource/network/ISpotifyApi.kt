package com.sixbits.assessment.feature.search.datasource.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ISpotifyApi {
    @GET("v1/search")
    fun searchSpotify(
        @Query("q") query: String,
        @Query("type") type: String = "track,artist",
        @Header("Authorization") authToken: String
    ): Call<SearchResponse>

    @GET("v1/artists/{id}")
    fun getArtistDetails(
        @Path("id") id: String,
        @Header("Authorization") authToken: String
    ): Call<ArtistDetailsResponse>

    @GET("v1/tracks/{id}")
    fun getTrackDetails(
        @Path("id") id: String,
        @Header("Authorization") authToken: String
    ): Call<TrackDetailsResponse>
}
