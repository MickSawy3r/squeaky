package com.sixbits.assessment.feature.search.datasource.local

import com.sixbits.assessment.feature.search.cache.CacheMapper
import com.sixbits.assessment.feature.search.domain.datamodel.ArtistDetailsDataModel
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel
import com.sixbits.assessment.feature.search.domain.datamodel.TrackDetailsDataModel
import javax.inject.Inject

class SearchLocalDataSource @Inject constructor(
    val cache: ISearchCache,
    private val cacheMapper: CacheMapper
) {
    suspend fun saveCache(response: ArtistDetailsDataModel) = cache.saveCache(
        response.toCacheEntry()
    )

    suspend fun saveCache(response: TrackDetailsDataModel) = cache.saveCache(
        response.toCacheEntry()
    )

    suspend fun getCachedRequests(): List<SpotifyDataModel> = cache.getCachedRequests()
        .map(cacheMapper::map)
}
