package com.maha.nasatest.feature.photoDetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.maha.nasatest.data.entities.Photo
import com.maha.nasatest.databinding.FragmentRoverItemDetailBinding
import com.maha.nasatest.databinding.FragmentRoverPhotoListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RoverItemDetailFragment : Fragment() {

    private lateinit var binding: FragmentRoverItemDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoverItemDetailBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photo = (arguments?.getBundle("rover_detail")) as Photo
        setupViews(photo)

    }

    fun setupViews(photo: Photo){
        val image = photo.img_src.replace("http","https")
        Glide
            .with(binding.ivRoverImage.context)
            .load(image)
            .centerCrop()
            .into(binding.ivRoverImage)    }

}