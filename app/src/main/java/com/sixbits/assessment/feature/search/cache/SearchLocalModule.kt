package com.sixbits.assessment.feature.search.cache

import android.content.Context
import androidx.room.Room
import com.sixbits.assessment.feature.search.datasource.local.ISearchCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchLocalModule {
    @Provides
    @Singleton
    fun provideCacheDatabase(@ApplicationContext context: Context): CacheDatabase {
        return Room
            .databaseBuilder(
                context,
                CacheDatabase::class.java,
                "cache.db"
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideCacheDao(cacheDb: CacheDatabase): CacheDao {
        return cacheDb.cacheDao()
    }

    @Provides
    @Singleton
    fun provideSearchCache(cacheDao: CacheDao): ISearchCache {
        return SearchCache(cacheDao)
    }
}
