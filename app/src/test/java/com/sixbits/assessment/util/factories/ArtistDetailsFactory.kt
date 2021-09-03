package com.sixbits.assessment.util.factories

import com.sixbits.assessment.feature.search.domain.datamodel.ArtistDetailsDataModel

object ArtistDetailsFactory {
    fun generateArtistDetails(name: String = "Mick", id: String = "123") = ArtistDetailsDataModel(
        followers = 3,
        name = name,
        genres = listOf("Pop"),
        id = id,
        image = "https://google.com"
    )
}
