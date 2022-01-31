package me.rail.ideasworldtest.screens.photos.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.rail.ideasworldtest.R
import me.rail.ideasworldtest.databinding.FragmentItemBinding
import me.rail.ideasworldtest.models.item.Item

private const val ARG_ID = "id"

@AndroidEntryPoint
class ItemFragment: Fragment() {

    private lateinit var binding: FragmentItemBinding

    private var id: String? = null

    private val model: ItemFragmentViewModel by viewModels()

    private lateinit var item: Item

    private var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ARG_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemBinding.inflate(inflater, container, false)

        setupItem()
        setupLikeButton()

        return binding.root
    }

    private fun setupItem() {
        lifecycleScope.launch {
            id?.let {
                item = model.getItem(it)

                binding.item.load(item.urls.full)
                binding.description.text = item.description
            }
        }
    }

    private fun setupLikeButton() {
        model.isLiked.observe(viewLifecycleOwner) {
            isLiked = it

            setLikeBackground(isLiked)
        }

        binding.like.setOnClickListener {
            setLikeBackground(!isLiked)

            if (!isLiked) {
                model.addItemToFavorite(item)
            } else {
                model.removeItemFromFavorite(item)
            }

            isLiked = !isLiked
        }
    }

    private fun setLikeBackground(isLiked: Boolean) {
        binding.like.background = if (isLiked) {
            AppCompatResources.getDrawable(requireContext(), R.drawable.liked)
        } else {
            AppCompatResources.getDrawable(requireContext(), R.drawable.favorites)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, id)
                }
            }
    }
}