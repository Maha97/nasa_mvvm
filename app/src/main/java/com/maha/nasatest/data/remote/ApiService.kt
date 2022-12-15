package com.maha.nasatest.data.remote

import com.maha.nasatest.data.remote.CloudConfig.MARS_PHOTOS
import com.maha.nasatest.data.entities.Photo
import com.maha.nasatest.data.entities.PhotoListReponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(MARS_PHOTOS)
    suspend fun getRoverPhotos(@Query("page") page:Int) : PhotoListReponse

}