package com.sixbits.assessment.feature.search.datasource.network

import com.sixbits.assessment.core.mapper.Mapper
import com.sixbits.assessment.feature.search.domain.datamodel.TrackDetailsDataModel

class TrackDetailsResponseMapper : Mapper<TrackDetailsResponse, TrackDetailsDataModel> {
    override fun map(input: TrackDetailsResponse): TrackDetailsDataModel = TrackDetailsDataModel(
        id = input.id,
        artist = input.artists[0].name,
        image = input.album.images[0].url,
        year = input.album.releaseDate,
        name = input.name
    )
}
