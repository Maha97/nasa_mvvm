package com.maha.nasatest.data.repository

import androidx.paging.PagingData
import com.maha.nasatest.data.entities.Photo
import java.util.concurrent.Flow

interface RoverRepository  {

    suspend fun getRoverPhotos( page :Int):List<Photo>
}