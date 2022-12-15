package com.maha.nasatest.data.repository

import com.appmattus.kotlinfixture.kotlinFixture
import com.maha.nasatest.data.entities.Photo
import com.maha.nasatest.data.entities.PhotoListReponse
import com.maha.nasatest.data.remote.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito


class RoverRepositoryImplTest{

    @Mock
    lateinit var apiService: ApiService

    private lateinit var roverRepository: RoverRepositoryImpl
    val fixture = kotlinFixture()

    @Before
    fun setup(){
     roverRepository = RoverRepositoryImpl(apiService)
    }

     @Test
     fun getRoverDataSucceded(){
         val photoList = fixture<List<Photo>>()
         val photoListResponse = PhotoListReponse(photoList)
         runBlocking{
             Mockito.`when`(apiService.getRoverPhotos(1))
                 .thenReturn(photoListResponse)
         }
         // Todo Continue assertion

     }


 }