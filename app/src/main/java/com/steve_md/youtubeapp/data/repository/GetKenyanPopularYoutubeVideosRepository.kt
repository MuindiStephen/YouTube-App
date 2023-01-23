package com.steve_md.youtubeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.steve_md.youtubeapp.data.dto.YoutubeResponse
import com.steve_md.youtubeapp.data.network.api.ApiService
import com.steve_md.youtubeapp.data.network.api.pagingsource.YoutubeKenyanPopularVideosPagingSource
import retrofit2.Response
import javax.inject.Inject

/**
 * Repository to interact with the API
 * */
class GetKenyanPopularYoutubeVideosRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getYoutubeVideos() = Pager(
        config = PagingConfig(
            pageSize = 30,
            maxSize = 150,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {YoutubeKenyanPopularVideosPagingSource(apiService)}
    )

    /*
    suspend fun getKenyanPopularYoutubeVideos() : Response<YoutubeResponse> {
        return apiService.getYoutubeVideos()
    }*/
}