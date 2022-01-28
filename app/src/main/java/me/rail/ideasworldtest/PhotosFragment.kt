package me.rail.ideasworldtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import me.rail.ideasworldtest.databinding.FragmentPhotosBinding

@AndroidEntryPoint
class PhotosFragment: Fragment() {

    private lateinit var binding: FragmentPhotosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)

        return binding.root
    }
}