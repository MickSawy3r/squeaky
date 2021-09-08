package com.sixbits.assessment.feature.search.datasource.local

interface ISearchCache {
    suspend fun saveCache(cacheEntry: CacheEntry)

    suspend fun saveCacheList(cacheEntry: List<CacheEntry>)

    suspend fun getCachedRequests(): List<CacheEntry>
}
