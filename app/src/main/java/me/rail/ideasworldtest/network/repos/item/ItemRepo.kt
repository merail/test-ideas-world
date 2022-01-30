package me.rail.ideasworldtest.network.repos.item

import me.rail.ideasworldtest.models.item.Item

interface ItemRepo {
    suspend fun getItem(id: String): Item
}