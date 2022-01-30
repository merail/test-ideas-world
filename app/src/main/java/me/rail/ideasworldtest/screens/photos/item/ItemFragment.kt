package me.rail.ideasworldtest.screens.photos.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.rail.ideasworldtest.databinding.FragmentItemBinding

private const val ARG_ID = "id"

@AndroidEntryPoint
class ItemFragment: Fragment() {

    private lateinit var binding: FragmentItemBinding

    private var id: String? = null

    private val model: ItemFragmentViewModel by viewModels()

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

        lifecycleScope.launch {
            id?.let {
                val item = model.getItem(it)

                binding.item.load(item.urls.full)
                binding.description.text = item.description
            }
        }

        return binding.root
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