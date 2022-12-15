package com.maha.nasatest.feature.photoList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.maha.nasatest.R
import com.maha.nasatest.data.entities.Photo
import com.maha.nasatest.databinding.FragmentRoverPhotoListBinding
import com.maha.nasatest.feature.photoList.viewmodel.RoverViewModel
import com.maha.nasatest.feature.photoList.view.adapter.PhotoItemAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RoverPhotoListFragment : Fragment(), PhotoItemAdapter.PhotoItemClickListener {

    private val viewModel: RoverViewModel by viewModels()

    private lateinit var binding: FragmentRoverPhotoListBinding
    private lateinit var mlayoutManager: LinearLayoutManager
    lateinit var photoAdapter: PhotoItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoverPhotoListBinding.inflate(layoutInflater)
        val view = binding.root
        viewModel.getPhotoList(1)

        setupRecyclerView()
        observeViewModel()
        return view
    }


    fun observeViewModel() {
        viewModel.photoListLiveData.observe(viewLifecycleOwner, { photoList ->
            photoAdapter.swap(photoList)

        })
    }

    fun setupRecyclerView() {
        photoAdapter = PhotoItemAdapter(items = mutableListOf(), this)
        mlayoutManager = LinearLayoutManager(requireContext())
        binding.rvPhotoList.run {
            layoutManager = mlayoutManager
            hasFixedSize()
        }
        binding.rvPhotoList.adapter = photoAdapter

    }

    override fun onClick(item: Photo) {
        val bundle = bundleOf("item" to item)
        findNavController().navigate(R.id.nav_roverlist_to_roverDetail, bundle)
    }

}