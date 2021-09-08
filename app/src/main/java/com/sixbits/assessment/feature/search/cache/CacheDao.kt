package com.sixbits.assessment.feature.search.cache

import androidx.room.Dao
import androidx.room.Query
import com.sixbits.assessment.core.db.BaseCacheDao
import com.sixbits.assessment.feature.search.datasource.local.CacheEntry
import java.util.Date

@Dao
abstract class CacheDao : BaseCacheDao<CacheEntry> {
    @Query("SELECT * FROM cache ORDER BY date DESC")
    abstract fun getCache(): List<CacheEntry>

    @Query("SELECT * FROM cache ORDER BY id DESC LIMIT 1")
    abstract fun getLast(): CacheEntry

    @Query("SELECT * FROM cache where date = :date")
    abstract fun getCacheAtDate(date: Date): List<CacheEntry>

    @Query("SELECT * FROM cache WHERE date = (SELECT MAX(date) FROM cache)")
    abstract fun getLatestCache(): List<CacheEntry>
}
