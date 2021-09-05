package com.sixbits.assessment.feature.search.cache

import com.sixbits.assessment.core.mapper.Mapper
import com.sixbits.assessment.feature.search.datasource.local.CacheEntry
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel
import javax.inject.Inject

class CacheMapper @Inject constructor() : Mapper<CacheEntry, SpotifyDataModel> {
    override fun map(input: CacheEntry): SpotifyDataModel {
        return SpotifyDataModel(
            name = input.name,
            type = input.type,
            id = input.itemId,
            image = input.image
        )
    }
}
