package com.maha.nasatest.feature.photoDetail.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.maha.nasatest.data.entities.Photo
import com.maha.nasatest.databinding.ActivityMainBinding
import com.maha.nasatest.databinding.FragmentRoverItemDetailBinding
import com.maha.nasatest.feature.photoList.viewmodel.RoverViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RoverItemDetailFragment : Fragment() {

    private lateinit var binding: FragmentRoverItemDetailBinding
    private val viewModel: RoverViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoverItemDetailBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    fun observeViewModel() {
        viewModel.selectedItem.observe(viewLifecycleOwner, Observer {
                photo ->
            setupViews(photo)
        }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }


    fun setupViews(photo: Photo) {
        val image = photo.img_src.replace("http", "https")
        Log.d("RoverItemDetailFragment", image)
        Glide
            .with(this)
            .load(image)
            .centerCrop()
            .into(binding.ivRoverDetailImage)
        binding.tvRoverName.setText(photo.rover.name)


    }
}