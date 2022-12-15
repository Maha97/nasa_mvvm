package com.maha.nasatest.data.repository

import com.maha.nasatest.data.entities.Photo

interface RoverRepository  {

    suspend fun getRoverPhotos( page :Int):List<Photo>
}