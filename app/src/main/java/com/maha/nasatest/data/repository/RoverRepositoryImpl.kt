package com.maha.nasatest.data.repository

import com.maha.nasatest.data.remote.ApiService
import com.maha.nasatest.data.responses.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoverRepositoryImpl @Inject constructor(private val apiService: ApiService) : RoverRepository {

    override suspend fun getRoverPhotos(page:Int): List<Photo> {
        return withContext(Dispatchers.IO) {
            apiService.getRoverPhotos(page = page)
        }
    }
}