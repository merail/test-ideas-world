package me.rail.ideasworldtest.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import me.rail.ideasworldtest.main.Navigator
import me.rail.ideasworldtest.R
import me.rail.ideasworldtest.databinding.FragmentMainBinding
import me.rail.ideasworldtest.screens.photos.PhotosFragment
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment: Fragment() {
    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var navigator: Navigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        setupBottomNavigationView()

        return binding.root
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigation.selectedItemId = R.id.photosPage
        navigator.addFragment(PhotosFragment())
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.favoritesPage ->
                    navigator.addFragment(FavoritesFragment())
                R.id.photosPage ->
                    navigator.addFragment(PhotosFragment())
                else ->
                    navigator.addFragment(InfoFragment())
            }

            return@setOnItemSelectedListener true
        }
    }
}