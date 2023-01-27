package com.steve_md.youtubeapp.data.network.api.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.steve_md.youtubeapp.data.dto.Item
import com.steve_md.youtubeapp.data.network.api.ApiService

class YoutubeKenyanPopularVideosPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val page: Int? = params.key          // Elvis operator return a not null current page of data

            val youtubeObject = apiService.getYoutubeVideos()
            LoadResult.Page(
                    data = youtubeObject.body()!!.items,
                    prevKey = null,
                    nextKey = page
            )
        } catch (e: Exception) {
               LoadResult.Error(e)
        }
    }
}