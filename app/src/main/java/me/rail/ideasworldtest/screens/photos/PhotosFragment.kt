package me.rail.ideasworldtest.screens.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.rail.ideasworldtest.databinding.FragmentPhotosBinding

@AndroidEntryPoint
class PhotosFragment: Fragment() {

    private lateinit var binding: FragmentPhotosBinding

    private val model: PhotosFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)

        binding.list.layoutManager = GridLayoutManager(requireContext(), 5)

        model.photos.observe(viewLifecycleOwner) {
            binding.list.adapter = PhotosAdapter(it)
        }

        return binding.root
    }
}