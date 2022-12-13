package com.maha.nasatest.data.repository

import com.maha.nasatest.data.remote.ApiService
import com.maha.nasatest.data.responses.Photo
import javax.inject.Inject

interface RoverRepository  {

    suspend fun getRoverPhotos( page :Int):List<Photo>
}