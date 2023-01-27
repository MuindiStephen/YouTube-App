package com.steve_md.youtubeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.steve_md.youtubeapp.data.dto.Item
import com.steve_md.youtubeapp.data.network.api.ApiService
import com.steve_md.youtubeapp.data.network.api.pagingsource.YoutubeKenyanPopularVideosPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Repository to interact with the API
 * */
class GetKenyanPopularYoutubeVideosRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getYoutubeVideos() : Flow<PagingData<Item>> = Pager(
        config = PagingConfig(
            pageSize = 30,
            maxSize = 150,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {YoutubeKenyanPopularVideosPagingSource(apiService)}
    ).flow

    /*
    suspend fun getKenyanPopularYoutubeVideos() : Response<YoutubeResponse> {
        return apiService.getYoutubeVideos()
    }*/
}