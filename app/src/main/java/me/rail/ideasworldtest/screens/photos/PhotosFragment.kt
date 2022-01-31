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
import me.rail.ideasworldtest.main.Navigator
import me.rail.ideasworldtest.models.list.Photo
import me.rail.ideasworldtest.network.ApiResult
import me.rail.ideasworldtest.screens.photos.item.ItemFragment
import javax.inject.Inject

@AndroidEntryPoint
class PhotosFragment: Fragment() {

    private lateinit var binding: FragmentPhotosBinding

    private val model: PhotosFragmentViewModel by viewModels()

    private lateinit var photosAdapter: PhotosAdapter

    @Inject
    lateinit var navigator: Navigator

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

        handleApiResult()
    }

    private fun setupRecyclerView() {
        binding.list.layoutManager = GridLayoutManager(requireContext(), 5)
        photosAdapter = PhotosAdapter {
            navigator.replaceFragment(ItemFragment.newInstance(it))
        }
        binding.list.adapter = photosAdapter
    }

    private fun setupSwipeRefreshLayout() {
        model.refreshState.observe(viewLifecycleOwner) {
            when (it) {
                is RefreshState.IsRefreshing -> {
                    updateList(it.photos)
                }
                else -> {
                }
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            model.setRefreshState(true)
        }
    }

    private fun handleApiResult() {
        lifecycleScope.launch {
            when (val apiResult = model.getPhotos()) {
                is ApiResult.Success -> {
                    binding.errorMessage.visibility = View.GONE
                    binding.preLoader.visibility = View.GONE
                    binding.list.visibility = View.VISIBLE

                    photosAdapter.setPhotos(apiResult._data)
                }
                else -> {
                    binding.preLoader.visibility = View.GONE
                    binding.errorMessage.text = apiResult.message
                    binding.errorMessage.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun updateList(photos: MutableList<Photo>?) {
        photosAdapter.clear()
        photosAdapter.addAll(photos)
        binding.swipeRefreshLayout.isRefreshing = false
        model.setRefreshState(false)
    }
}