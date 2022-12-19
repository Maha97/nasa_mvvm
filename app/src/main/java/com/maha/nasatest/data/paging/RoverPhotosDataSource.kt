package com.maha.nasatest.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.maha.nasatest.data.entities.Photo
import com.maha.nasatest.data.remote.ApiService
import com.maha.nasatest.data.repository.RoverRepository
import java.io.IOException
import javax.inject.Inject


private const val ROVER_STARTING_PAGE_INDEX = 1
class RoverPhotosDataSource @Inject constructor(private val repo :RoverRepository) :
    PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: ROVER_STARTING_PAGE_INDEX
        return try {
            val response = repo.getRoverPhotos(ROVER_STARTING_PAGE_INDEX)
            val photos = response
            val nextKey = if (photos!!.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / 10)
            }
            LoadResult.Page(
                data = photos,
                prevKey = if (position == ROVER_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


}