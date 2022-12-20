package com.maha.nasatest.feature.photoList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.maha.nasatest.R
import com.maha.nasatest.data.entities.Photo
import com.maha.nasatest.databinding.FragmentRoverPhotoListBinding
import com.maha.nasatest.feature.photoList.viewmodel.RoverViewModel
import com.maha.nasatest.feature.photoList.view.adapter.PhotoListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class RoverPhotoListFragment : Fragment(), PhotoListAdapter.PhotoItemClickListener {

    private val viewModel: RoverViewModel by activityViewModels()

    private lateinit var binding: FragmentRoverPhotoListBinding
    private lateinit var mlayoutManager: LinearLayoutManager
    lateinit var photoAdapter: PhotoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoverPhotoListBinding.inflate(layoutInflater)
        val view = binding.root

        setupRecyclerView()
        observeViewModel()
        return view
    }


    fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.roverPhotos.collectLatest { pagingData ->
                photoAdapter.submitData(pagingData)
            }
        }
    }

    fun setupRecyclerView() {
       mlayoutManager = LinearLayoutManager(requireContext())
        binding.rvPhotoList.run {
            layoutManager = mlayoutManager
            hasFixedSize()
        }
        photoAdapter = PhotoListAdapter(this)
        binding.rvPhotoList.adapter = photoAdapter

    }

    override fun onClick(item: Photo) {
        viewModel.selectItem(item)
        findNavController().navigate(R.id.nav_roverlist_to_roverDetail)

    }

}
