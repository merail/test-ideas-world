package me.rail.ideasworldtest.screens.photos.item

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import me.rail.ideasworldtest.models.item.Item
import me.rail.ideasworldtest.network.repos.item.ItemRepo
import javax.inject.Inject

@HiltViewModel
class ItemFragmentViewModel @Inject constructor(
    private val itemRepo: ItemRepo
): ViewModel() {
    suspend fun getItem(id: String): Item {
        return itemRepo.getItem(id)
    }
}