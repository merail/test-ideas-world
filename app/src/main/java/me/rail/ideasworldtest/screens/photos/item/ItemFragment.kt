package me.rail.ideasworldtest.screens.photos.item

import android.graphics.PorterDuff
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.rail.ideasworldtest.R
import me.rail.ideasworldtest.databinding.FragmentItemBinding
import me.rail.ideasworldtest.models.item.Item
import me.rail.ideasworldtest.network.ApiResult


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

        binding.root.isClickable = true

        binding.preLoader.indeterminateDrawable
            .setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.dark),
                PorterDuff.Mode.SRC_IN
            )
        setupItem()
        setupLikeButton()

        return binding.root
    }

    private fun setupItem() {
        lifecycleScope.launch {
            id?.let {
                when (val apiResult = model.getItem(it)) {
                    is ApiResult.Success -> {
                        binding.errorMessage.visibility = View.GONE
                        binding.preLoader.visibility = View.GONE
                        binding.itemLayout.visibility = View.VISIBLE

                        item = apiResult._data!!

                        binding.item.load(item.urls.full)
                        binding.description.movementMethod = ScrollingMovementMethod()
                        item.description?.let {
                            binding.description.text = it
                        }
                    }
                    else -> {
                        binding.preLoader.visibility = View.GONE
                        binding.errorMessage.text = apiResult.message
                        binding.errorMessage.visibility = View.VISIBLE
                    }
                }
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