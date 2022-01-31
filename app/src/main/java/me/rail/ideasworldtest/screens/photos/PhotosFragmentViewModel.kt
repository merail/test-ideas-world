package me.rail.ideasworldtest.screens.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.rail.ideasworldtest.models.list.Photo
import me.rail.ideasworldtest.network.ApiResult
import me.rail.ideasworldtest.network.repos.list.PhotoRepo
import javax.inject.Inject

@HiltViewModel
class PhotosFragmentViewModel @Inject constructor(
    private val photoRepo: PhotoRepo
): ViewModel() {

    private val _refreshState = MutableLiveData<RefreshState>(RefreshState.IsNotRefreshing)
    val refreshState: LiveData<RefreshState> get() = _refreshState

    suspend fun getPhotos(): ApiResult<MutableList<Photo>> {
        return photoRepo.getPhotos()
    }

    fun setRefreshState(isRefreshing: Boolean) {
        if (isRefreshing) {
            viewModelScope.launch {
                when (val apiResult = getPhotos()) {
                    is ApiResult.Success -> {
                        _refreshState.value = RefreshState.IsRefreshing(apiResult._data)
                    }
                    else -> {
                        _refreshState.value = RefreshState.IsNotRefreshing
                    }
                }
            }
        } else {
            _refreshState.value = RefreshState.IsNotRefreshing
        }
    }
}