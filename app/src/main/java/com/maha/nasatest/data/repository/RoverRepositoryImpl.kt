package com.maha.nasatest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.maha.nasatest.data.remote.ApiService
import com.maha.nasatest.data.entities.Photo
import com.maha.nasatest.data.paging.RoverPhotosDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoverRepositoryImpl @Inject constructor(private val apiService: ApiService) : RoverRepository {

    override suspend fun getRoverPhotos(page:Int): List<Photo> {
        return withContext(Dispatchers.IO) {
            apiService.getRoverPhotos(page = page).photos!!
        }
    }
    }
