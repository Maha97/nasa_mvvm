package com.maha.nasatest.feature.photoList.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maha.nasatest.data.entities.Photo
import com.maha.nasatest.databinding.ItemPhotoBinding

class PhotoListAdapter(private val listener: PhotoItemClickListener
) :  PagingDataAdapter<Photo, PhotoListAdapter.PhotoViewHolder>(PhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    inner class PhotoViewHolder(private  val binding: ItemPhotoBinding) :
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
    override fun onBindViewHolder(
        holder: PhotoViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val newItem = payloads[0] as Photo
            holder.bind(newItem)
        }
    }

    class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Photo,
            newItem: Photo
        ) = oldItem == newItem

        override fun getChangePayload(oldItem: Photo, newItem: Photo): Any? {
            if (oldItem != newItem) {
                return newItem
            }

            return super.getChangePayload(oldItem, newItem)
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item: Photo? = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    interface PhotoItemClickListener {
        fun onClick(item: Photo)
    }
}

