package com.steve_md.youtubeapp.data.repository

import com.steve_md.youtubeapp.data.dto.YoutubeResponse
import com.steve_md.youtubeapp.data.network.api.ApiService
import retrofit2.Response
import javax.inject.Inject

/**
 * Repository to interact with the API
 * */
class GetKenyanPopularYoutubeVideosRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getKenyanPopularYoutubeVideos() : Response<YoutubeResponse> {
        return apiService.getYoutubeVideos()
    }
}