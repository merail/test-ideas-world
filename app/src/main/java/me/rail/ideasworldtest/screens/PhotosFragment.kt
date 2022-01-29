package me.rail.ideasworldtest.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
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

        binding.list.layoutManager = GridLayoutManager(requireContext(), 5)

        lifecycleScope.launch {
            val photos = photoRepo.getPhotos()
            binding.list.adapter = PhotosAdapter(photos)
        }

        return binding.root
    }
}