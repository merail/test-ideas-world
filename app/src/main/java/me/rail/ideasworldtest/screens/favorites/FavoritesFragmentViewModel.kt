package me.rail.ideasworldtest.screens.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.rail.ideasworldtest.db.FavoriteItemsDao
import me.rail.ideasworldtest.models.item.Item
import me.rail.ideasworldtest.network.repos.item.ItemRepo
import javax.inject.Inject

@HiltViewModel
class FavoritesFragmentViewModel @Inject constructor(private val favoriteItemsDao: FavoriteItemsDao): ViewModel() {

    private val _state = MutableLiveData<FavoriteItemsState>(FavoriteItemsState.Loading)
    val state: LiveData<FavoriteItemsState> = _state

    init {
        viewModelScope.launch {
            favoriteItemsDao.getAll().collect {
                _state.value = FavoriteItemsState.Showing(it)
            }
        }
    }
}
