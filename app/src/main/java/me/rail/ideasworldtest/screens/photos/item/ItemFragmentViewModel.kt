package me.rail.ideasworldtest.screens.photos.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.rail.ideasworldtest.db.FavoriteItem
import me.rail.ideasworldtest.db.FavoriteItemsDao
import me.rail.ideasworldtest.models.item.Item
import me.rail.ideasworldtest.network.repos.item.ItemRepo
import javax.inject.Inject

@HiltViewModel
class ItemFragmentViewModel @Inject constructor(
    private val itemRepo: ItemRepo,
    private val favoriteItemsDao: FavoriteItemsDao
): ViewModel() {

    private lateinit var id: String

    suspend fun getItem(id: String): Item {
        this.id = id
        return itemRepo.getItem(id)
    }

    fun addItemToFavorite(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteItemsDao.insert(FavoriteItem(item.id, item.urls.small))
        }
    }

    fun removeItemFromFavorite(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteItemsDao.delete(FavoriteItem(item.id, item.urls.small))
        }
    }

    val isLiked = liveData {
        val isLiked = getIsLiked()
        emit(isLiked)
    }

    private suspend fun getIsLiked() = withContext(Dispatchers.IO) {
        favoriteItemsDao.exists(id)
    }
}