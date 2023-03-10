package com.steve_md.youtubeapp.data.dto


import com.google.gson.annotations.SerializedName
/**
 * YouTubeVideo DataClass
 * */
data class Item(
    @SerializedName("contentDetails")
    val contentDetails: ContentDetails,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("snippet")
    val snippet: Snippet,  /**Video details*/
    @SerializedName("statistics")
    val statistics: Statistics
)