package me.rail.ideasworldtest.screens.favorites

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import me.rail.ideasworldtest.databinding.ItemPhotoBinding
import me.rail.ideasworldtest.db.FavoriteItem
import me.rail.ideasworldtest.models.item.Item
import me.rail.ideasworldtest.models.list.Photo

class FavoriteItemsAdapter(private val onItemClick: ((String) -> Unit)? = null):
    RecyclerView.Adapter<FavoriteItemsAdapter.PhotoViewHolder>() {
    private var items: List<FavoriteItem> ?= null

    class PhotoViewHolder(val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = items?.get(position) ?: return

        holder.binding.photo.load(item.urlSmall)
        holder.binding.photo.setOnClickListener {
            onItemClick?.invoke(item.id)
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<FavoriteItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}