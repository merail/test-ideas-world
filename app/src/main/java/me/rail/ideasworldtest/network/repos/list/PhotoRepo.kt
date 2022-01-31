package me.rail.ideasworldtest.network.repos.list

import me.rail.ideasworldtest.models.list.Photo
import me.rail.ideasworldtest.network.ApiResult

interface PhotoRepo {
    suspend fun getPhotos(): ApiResult<MutableList<Photo>>
}