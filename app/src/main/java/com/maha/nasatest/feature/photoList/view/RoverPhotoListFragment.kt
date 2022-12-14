package com.maha.nasatest.feature.photoList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maha.nasatest.databinding.FragmentRoverPhotoListBinding
import com.maha.nasatest.feature.photoList.PhotoItemAdapter
import com.maha.nasatest.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RoverPhotoListFragment :Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentRoverPhotoListBinding
    private lateinit var mlayoutManager: LinearLayoutManager
    lateinit var photoAdapter : PhotoItemAdapter

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


   fun observeViewModel(){
         viewModel.photoListLiveData.observe(viewLifecycleOwner, {
                 photoList ->
             photoAdapter.swap(photoList)

         } )
    }
   fun  setupRecyclerView(){
       photoAdapter = PhotoItemAdapter(items = mutableListOf())
       mlayoutManager = LinearLayoutManager(requireContext())
       binding.rvPhotoList.run {
           layoutManager = mlayoutManager
           hasFixedSize()
       }

    }
}