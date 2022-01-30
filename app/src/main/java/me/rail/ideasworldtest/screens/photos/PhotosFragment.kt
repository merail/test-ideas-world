package me.rail.ideasworldtest.screens.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.rail.ideasworldtest.databinding.FragmentPhotosBinding
import me.rail.ideasworldtest.models.list.Photo

@AndroidEntryPoint
class PhotosFragment: Fragment() {

    private lateinit var binding: FragmentPhotosBinding

    private val model: PhotosFragmentViewModel by viewModels()

    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)

        setupRecyclerView()
        setupSwipeRefreshLayout()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            photosAdapter.setPhotos(model.getPhotos())
        }
    }

    private fun setupRecyclerView() {
        binding.list.layoutManager = GridLayoutManager(requireContext(), 5)
        photosAdapter = PhotosAdapter()
        binding.list.adapter = photosAdapter
    }

    private fun setupSwipeRefreshLayout() {
        model.refreshState.observe(viewLifecycleOwner) {
            when (it) {
                is RefreshState.IsRefreshing -> updateList(it.photos)
                else -> {
                }
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            model.setRefreshState(true)
        }
    }

    private fun updateList(photos: MutableList<Photo>) {
        photosAdapter.clear()
        photosAdapter.addAll(photos)
        binding.swipeRefreshLayout.isRefreshing = false
        model.setRefreshState(false)
    }
}