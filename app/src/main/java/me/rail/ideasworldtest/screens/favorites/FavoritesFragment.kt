package me.rail.ideasworldtest.screens.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.rail.ideasworldtest.databinding.FragmentFavoritesBinding
import me.rail.ideasworldtest.main.Navigator
import me.rail.ideasworldtest.screens.photos.PhotosAdapter
import me.rail.ideasworldtest.screens.photos.item.ItemFragment
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment: Fragment() {

    private lateinit var binding: FragmentFavoritesBinding

    private lateinit var favoriteItemsAdapter: FavoriteItemsAdapter

    @Inject
    lateinit var navigator: Navigator

    private val model: FavoritesFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.state.observe(viewLifecycleOwner) {
            when (it) {
                is FavoriteItemsState.Showing -> {
                    favoriteItemsAdapter.setItems(it.items)
                }
                else -> {}
            }
        }
    }

    private fun setupRecyclerView() {
        binding.favoritesList.layoutManager = GridLayoutManager(requireContext(), 5)
        favoriteItemsAdapter = FavoriteItemsAdapter {
            navigator.replaceFragment(ItemFragment.newInstance(it))
        }
        binding.favoritesList.adapter = favoriteItemsAdapter
    }
}