package com.sixbits.assessment.feature.search.datasource.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ISpotifyApi {
    @GET("v1/search")
    fun searchSpotify(
        @Query("q") query: String,
        @Header("Authorization") authToken: String
    ): Single<SearchResponse>

    @GET("v1/artists/{id}")
    fun getArtistDetails(
        @Path("id") id: String,
        @Header("Authorization") authToken: String
    ): Single<ArtistDetailsResponse>

    @GET("v1/tracks/{id}")
    fun getTrackDetails(
        @Path("id") id: String,
        @Header("Authorization") authToken: String
    ): Single<TrackDetailsResponse>
}
