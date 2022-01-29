package me.rail.ideasworldtest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.rail.ideasworldtest.network.repos.list.HttpPhotoRepo
import me.rail.ideasworldtest.network.repos.list.PhotoRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ReposModule {

    @Binds
    @Singleton
    abstract fun photos(repo: HttpPhotoRepo): PhotoRepo
}