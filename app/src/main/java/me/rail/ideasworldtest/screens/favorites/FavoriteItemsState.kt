package me.rail.ideasworldtest.screens.favorites

import me.rail.ideasworldtest.db.FavoriteItem

sealed class FavoriteItemsState {
    object Loading: FavoriteItemsState()
    data class Showing(val items: List<FavoriteItem>): FavoriteItemsState()
}
