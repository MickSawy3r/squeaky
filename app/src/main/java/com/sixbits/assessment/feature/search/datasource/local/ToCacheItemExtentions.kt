package com.sixbits.assessment.feature.search.datasource.local

import com.sixbits.assessment.feature.search.domain.datamodel.ArtistDetailsDataModel
import com.sixbits.assessment.feature.search.domain.datamodel.SearchItemType
import com.sixbits.assessment.feature.search.domain.datamodel.TrackDetailsDataModel

fun ArtistDetailsDataModel.toCacheEntry() = CacheEntry(
    name = this.name,
    type = SearchItemType.ARTIST,
    itemId = this.id,
    image = this.image
)

fun TrackDetailsDataModel.toCacheEntry() = CacheEntry(
    name = this.name,
    type = SearchItemType.TRACK,
    itemId = this.id,
    image = this.image
)
