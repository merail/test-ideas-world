package me.rail.ideasworldtest.screens.photos

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import me.rail.ideasworldtest.databinding.ItemPhotoBinding
import me.rail.ideasworldtest.models.list.Photo

class PhotosAdapter:
    RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {
    private var photos: MutableList<Photo> ?= null

    class PhotoViewHolder(val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = photos?.get(position) ?: return

        holder.binding.photo.load(item.urls.small)
    }

    override fun getItemCount(): Int {
        return photos?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPhotos(photos: MutableList<Photo>) {
        this.photos = photos
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        photos?.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(photos: MutableList<Photo>) {
        this.photos?.addAll(photos)
        notifyDataSetChanged()
    }
}