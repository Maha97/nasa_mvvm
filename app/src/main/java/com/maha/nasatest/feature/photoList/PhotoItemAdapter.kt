package com.maha.nasatest.feature.photoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maha.nasatest.R
import com.maha.nasatest.data.responses.Photo
import com.maha.nasatest.databinding.ItemPhotoBinding

class PhotoItemAdapter (private val items :MutableList<Photo>) : RecyclerView.Adapter<PhotoItemAdapter.PhotoItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)
        return  PhotoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        val item = items[position]
        // set date Text
        holder.binding.tvDate.setText(item.rover.landing_date)
        holder.binding.roverName.setText(item.rover.name)
        Glide
            .with(holder.itemView.context)
            .load(item.img_src)
            .centerCrop()
            .into(holder.binding.ivPhoto);
    }

    inner class PhotoItemViewHolder(val binding :ItemPhotoBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun getItemCount(): Int {
          return items.size
    }
}