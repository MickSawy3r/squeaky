package com.sixbits.assessment.feature.search.datasource.local

import com.sixbits.assessment.feature.search.cache.CacheMapper
import com.sixbits.assessment.feature.search.domain.datamodel.ArtistDetailsDataModel
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel
import com.sixbits.assessment.feature.search.domain.datamodel.TrackDetailsDataModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchLocalDataSource @Inject constructor(
    val cache: ISearchCache,
    private val cacheMapper: CacheMapper
) {
    fun saveCache(response: ArtistDetailsDataModel): Completable = cache.saveCache(
        response.toCacheEntry()
    )

    fun saveCache(response: TrackDetailsDataModel): Completable = cache.saveCache(
        response.toCacheEntry()
    )

    fun getCachedRequests(): Single<List<SpotifyDataModel>> = cache.getCachedRequests()
        .map { it.map(cacheMapper::map) }
}
