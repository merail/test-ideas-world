package me.rail.ideasworldtest.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.rail.ideasworldtest.databinding.FragmentPhotosBinding
import me.rail.ideasworldtest.network.repos.list.PhotoRepo
import javax.inject.Inject

@AndroidEntryPoint
class PhotosFragment: Fragment() {

    private lateinit var binding: FragmentPhotosBinding

    @Inject
    lateinit var photoRepo: PhotoRepo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            val photos = photoRepo.getPhotos()
            Log.d("test", photos.toString())
            Log.d("test2", photos.size.toString())
        }

        return binding.root
    }
}