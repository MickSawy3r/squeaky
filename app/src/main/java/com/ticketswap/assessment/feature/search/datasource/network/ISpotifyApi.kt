package com.ticketswap.assessment.feature.search.datasource.network

import io.reactivex.rxjava3.core.Single

interface ISpotifyApi {
    fun searchSpotify(query: String, authToken: String): Single<SearchResponse>

    fun getArtistDetails(id: String, authToken: String): Single<ArtistDetailsResponse>

    fun getTrackDetails(id: String, authToken: String): Single<TrackDetailsResponse>
}
