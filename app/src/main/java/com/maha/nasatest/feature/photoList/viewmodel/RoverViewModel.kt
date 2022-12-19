package com.maha.nasatest.feature.photoList.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maha.nasatest.data.entities.Photo
import com.maha.nasatest.data.repository.RoverRepository
import com.maha.nasatest.data.paging.RoverPhotosDataSource
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RoverViewModel @Inject constructor(private val repo: RoverRepository) : ViewModel() {

    private val mutableSelectedItem = MutableLiveData<Photo>()
    val selectedItem: LiveData<Photo> get() = mutableSelectedItem

    fun selectItem(item: Photo) {
        mutableSelectedItem.value = item
    }
    val characters: Flow<PagingData<Photo>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { RoverPhotosDataSource(repo) }
        ).flow.cachedIn(viewModelScope)


}