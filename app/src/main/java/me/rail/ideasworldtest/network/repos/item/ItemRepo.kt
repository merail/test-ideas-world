package me.rail.ideasworldtest.network.repos.item

import me.rail.ideasworldtest.models.item.Item
import me.rail.ideasworldtest.network.ApiResult

interface ItemRepo {
    suspend fun getItem(id: String): ApiResult<Item>
}