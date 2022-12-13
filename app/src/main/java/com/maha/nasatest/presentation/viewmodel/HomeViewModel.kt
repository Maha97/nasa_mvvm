package com.maha.nasatest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maha.nasatest.data.remote.UseCaseResult
import com.maha.nasatest.data.repository.RoverRepository
import com.maha.nasatest.data.responses.Photo
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Flow
import javax.inject.Inject
import kotlinx.coroutines.launch


@InstallIn(ViewModelComponent::class)
class HomeViewModel @Inject constructor(private val repo :RoverRepository) : ViewModel() {

    var photoListLiveData = MutableLiveData<List<Photo>>()
       var showError = MutableLiveData<Any>()

    fun getPhotoList(page :Int){
        launch {
            val photoList = withContext(Dispatchers.IO) {
                repo.getRoverPhotos(page)
            }
            //showLoading.value = false
            when (photoList) {
                is UseCaseResult.OnSuccess<*> -> photoListLiveData.postValue(photoList)
                is UseCaseResult.OnError<*> -> showError.postValue(photoListLiveData)
            }
        }
    }


        // launch the Coroutine
/*
        launch{
            val result = withContext(Dispatchers.IO) {
                (repo.getRoverPhotos(1))
            }
            showLoading.value = false
            when (result) {
                is UseCaseResult.OnSuccess -> {
                    OTPRequestResponse.postValue(result.data)
                    //showInfo.postValue(R.string.enter_otp_code)
                }
                is UseCaseResult.OnError -> showError.value = result.exception.message
            }
        }
*/
    }

}