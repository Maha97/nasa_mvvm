package com.maha.nasatest.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maha.nasatest.data.remote.UseCaseResult
import com.maha.nasatest.data.repository.RoverRepository
import com.maha.nasatest.data.responses.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repo :RoverRepository) : ViewModel() {

    var photoListLiveData = MutableLiveData<List<Photo>>()

    fun getPhotoList(page :Int){
        launch {
            val photoList = withContext(Dispatchers.IO) {
                repo.getRoverPhotos(page)
            }
            //showLoading.value = false
            when (photoList) {
                is UseCaseResult.OnSuccess<*> -> photoListLiveData.postValue(photoList)
              //  is UseCaseResult.OnError<*> -> showError.postValue(photoListLiveData)
            }
        }
    }
}