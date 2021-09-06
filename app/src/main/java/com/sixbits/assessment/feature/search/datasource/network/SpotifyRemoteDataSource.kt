package com.sixbits.assessment.feature.search.datasource.network

import com.sixbits.assessment.feature.search.domain.datamodel.ArtistDetailsDataModel
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel
import com.sixbits.assessment.feature.search.domain.datamodel.TrackDetailsDataModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SpotifyRemoteDataSource @Inject constructor(
    private val iSpotifyApi: ISpotifyApi,
    private val artistDetailsResponseMapper: ArtistDetailsResponseMapper,
    private val searchListMapper: SearchResponseMapper,
    private val trackDetailsResponseMapper: TrackDetailsResponseMapper
) {

    fun search(query: String, authToken: String): Single<List<SpotifyDataModel>> {
        return iSpotifyApi.searchSpotify(
            query = query,
            authToken = authToken
        ).map(searchListMapper::map)
    }

    fun getArtistDetails(id: String, authToken: String): Single<ArtistDetailsDataModel> {
        return iSpotifyApi.getArtistDetails(
            id = id,
            authToken = authToken
        ).map(artistDetailsResponseMapper::map)
    }

    fun getTrackDetails(id: String, authToken: String): Single<TrackDetailsDataModel> {
        return iSpotifyApi.getTrackDetails(
            id = id,
            authToken = authToken
        ).map(trackDetailsResponseMapper::map)
    }
}
