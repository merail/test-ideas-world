package me.rail.ideasworldtest.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import me.rail.ideasworldtest.databinding.ItemPhotoBinding
import me.rail.ideasworldtest.models.list.Photo

class PhotosAdapter(private val photos: MutableList<Photo>):
    RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    class PhotoViewHolder(val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = photos[position]

        holder.binding.photo.load(item.urls.small)
    }

    override fun getItemCount(): Int {
        return photos.size
    }
}