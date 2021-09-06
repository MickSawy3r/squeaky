package com.sixbits.assessment.core.di

import android.content.Context
import com.sixbits.assessment.BuildConfig
import com.sixbits.assessment.feature.search.datasource.network.ArtistDetailsResponseMapper
import com.sixbits.assessment.feature.search.datasource.network.ISpotifyApi
import com.sixbits.assessment.feature.search.datasource.network.SearchResponseMapper
import com.sixbits.assessment.feature.search.datasource.network.TrackDetailsResponseMapper
import com.sixbits.authenticator.AuthGuard
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideAuthGuard(@ApplicationContext context: Context): AuthGuard {
        return AuthGuard(context)
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SPOTIFY_BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideSpotifyApi(retrofit: Retrofit): ISpotifyApi {
        return retrofit.create(ISpotifyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchMapper(): SearchResponseMapper = SearchResponseMapper()

    @Provides
    @Singleton
    fun provideArtistDetailsMapper(): ArtistDetailsResponseMapper = ArtistDetailsResponseMapper()

    @Provides
    @Singleton
    fun provideTrackDetailsMapper(): TrackDetailsResponseMapper = TrackDetailsResponseMapper()
}
