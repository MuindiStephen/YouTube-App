package com.steve_md.youtubeapp.data.dto


import com.google.gson.annotations.SerializedName

data class YoutubeResponse(
    @SerializedName("etag")
    val etag: String,
    @SerializedName("items")
    val items: List<Item>,   /**Returns List of Youtube Videos and their Properties**/
    @SerializedName("kind")
    val kind: String,
    @SerializedName("nextPageToken")
    val nextPageToken: String,
    @SerializedName("pageInfo")
    val pageInfo: PageInfo
)