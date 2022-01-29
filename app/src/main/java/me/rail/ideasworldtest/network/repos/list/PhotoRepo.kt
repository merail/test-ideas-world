package me.rail.ideasworldtest.network.repos.list

import me.rail.ideasworldtest.models.list.Photo

interface PhotoRepo {
    suspend fun getPhotos(): MutableList<Photo>
}