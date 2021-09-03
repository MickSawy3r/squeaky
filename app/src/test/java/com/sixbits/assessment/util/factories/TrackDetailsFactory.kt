package com.sixbits.assessment.util.factories

import com.sixbits.assessment.feature.search.domain.datamodel.TrackDetailsDataModel

object TrackDetailsFactory {
    fun generateTrackDetails(name: String = "Track", id: String = "123") = TrackDetailsDataModel(
        name = name,
        id = id,
        image = "https://google.com",
        year = "1/1/1996",
        artist = "Mick"
    )
}
