package me.rail.ideasworldtest.screens.photos

import me.rail.ideasworldtest.models.list.Photo

sealed class RefreshState {
    data class IsRefreshing(val photos: MutableList<Photo>?): RefreshState()
    object IsNotRefreshing: RefreshState()
}
