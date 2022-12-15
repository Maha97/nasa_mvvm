package com.maha.nasatest.feature.photoList.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maha.nasatest.data.entities.Photo
import com.maha.nasatest.databinding.ItemPhotoBinding

class PhotoItemAdapter(
    private val items: MutableList<Photo>,
    private val listener: PhotoItemClickListener
) : RecyclerView.Adapter<PhotoItemAdapter.PhotoItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)
        return PhotoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        holder.bind(items[position])

    }

    inner class PhotoItemViewHolder(val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Photo) {
            // set date Text
            itemView.setOnClickListener {
                listener.onClick(
                    item
                )
            }
            binding.tvLandingDate.setText(item.rover.landing_date)
            binding.tvRoverName.setText(item.rover.name)
            val image = item.img_src.replace("http", "https")
            Glide
                .with(itemView.context)
                .load(image)
                .centerCrop()
                .into(binding.ivRoverImage)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun swap(items: List<Photo>) {
        val diffCallback = PhotoDiffCallback(this.items, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.items.clear()
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    interface PhotoItemClickListener {
        fun onClick(item: Photo)
    }

}
