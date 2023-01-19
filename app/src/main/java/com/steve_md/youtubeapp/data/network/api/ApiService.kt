package com.steve_md.youtubeapp.data.network.api

import com.steve_md.youtubeapp.data.dto.Item
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/videos")
    suspend fun getYoutubeVideos(
        @Query("key") key: String = "AIzaSyAv_oO_G5E4rPu8WpSapzVR3sqHESPwdzg",
        @Query("regionCode") regionCode: String = "KE",
        @Query("part") part: String = "contentDetails,snippet,statistics",
        @Query("chart") chart : String = "mostPopular"
    ) : ArrayList<Item>
}