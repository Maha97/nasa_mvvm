package com.maha.nasatest.feature.photoList.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maha.nasatest.data.entities.Photo
import com.maha.nasatest.data.repository.RoverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class RoverViewModel @Inject constructor(private val repo: RoverRepository) : ViewModel() {

    var photoListLiveData = MutableLiveData<List<Photo>>()

    fun getPhotoList(page: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val photoList = withContext(Dispatchers.IO) {
                repo.getRoverPhotos(page)
            }
            photoListLiveData.postValue(photoList)
        }

    }
}