package me.rail.ideasworldtest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.rail.ideasworldtest.BuildConfig
import me.rail.ideasworldtest.network.HttpClient
import me.rail.ideasworldtest.network.HttpClientFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory(
            url = BuildConfig.BASE_URL,
            connectTimeout = 7_000L,
            readTimeout = 150_000L
        ).create()
    }
}