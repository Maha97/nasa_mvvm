package com.maha.nasatest.feature.photoList.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.maha.nasatest.data.entities.Photo

class PhotoDiffCallback(
        private val oldList: List<Photo>,
        private val newList: List<Photo>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }