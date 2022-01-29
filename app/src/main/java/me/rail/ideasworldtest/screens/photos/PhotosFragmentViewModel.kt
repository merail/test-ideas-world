package me.rail.ideasworldtest.screens.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import me.rail.ideasworldtest.network.repos.list.PhotoRepo
import javax.inject.Inject

@HiltViewModel
class PhotosFragmentViewModel @Inject constructor(
    private val photoRepo: PhotoRepo
) : ViewModel() {

    val photos = liveData {
        val photos = photoRepo.getPhotos()
        emit(photos)
    }
}