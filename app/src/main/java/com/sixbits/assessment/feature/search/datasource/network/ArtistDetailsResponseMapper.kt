package com.sixbits.assessment.feature.search.datasource.network

import com.sixbits.assessment.core.mapper.Mapper
import com.sixbits.assessment.feature.search.domain.datamodel.ArtistDetailsDataModel

class ArtistDetailsResponseMapper : Mapper<ArtistDetailsResponse, ArtistDetailsDataModel> {
    override fun map(input: ArtistDetailsResponse): ArtistDetailsDataModel = ArtistDetailsDataModel(
        name = input.name,
        image = input.images[0].url,
        id = input.id,
        genres = input.genres,
        followers = input.followers.total
    )
}
