package com.sixbits.assessment.feature.search.data

import com.sixbits.assessment.feature.search.datasource.local.CacheEntry
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel

fun SpotifyDataModel.toCacheEntry(): CacheEntry {
    return CacheEntry(
        name = this.name,
        type = this.type,
        itemId = this.id,
        image = this.image
    )
}

fun List<SpotifyDataModel>.toCacheEntryList(): List<CacheEntry> {
    return this.map {
        it.toCacheEntry()
    }
}
