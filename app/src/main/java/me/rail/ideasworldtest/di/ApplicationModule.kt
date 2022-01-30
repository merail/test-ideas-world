package me.rail.ideasworldtest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.rail.ideasworldtest.BuildConfig
import me.rail.ideasworldtest.db.FavoriteItemsDao
import me.rail.ideasworldtest.db.FavoriteItemsDatabase
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

    @Provides
    @Singleton
    fun provideFavoriteItemsDao(database: FavoriteItemsDatabase): FavoriteItemsDao {
        return database.favoriteItemsDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FavoriteItemsDatabase {
        return Room.databaseBuilder(context, FavoriteItemsDatabase::class.java, "favorite_items").build()
    }
}