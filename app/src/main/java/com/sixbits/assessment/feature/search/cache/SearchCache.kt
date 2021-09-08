package com.sixbits.assessment.feature.search.cache

import com.sixbits.assessment.feature.search.datasource.local.CacheEntry
import com.sixbits.assessment.feature.search.datasource.local.ISearchCache

class SearchCache constructor(private val cacheDao: CacheDao) : ISearchCache {
    override suspend fun saveCache(cacheEntry: CacheEntry) =
        cacheDao.save(cacheEntry)

    override suspend fun saveCacheList(cacheEntry: List<CacheEntry>) =
        cacheDao.saveMany(cacheEntry)

    override suspend fun getCachedRequests(): List<CacheEntry> =
        cacheDao.getCache()
}
