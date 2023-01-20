package com.steve_md.youtubeapp.util

object Constants {

    // Returns a list of videos that match the API request parameters.
    const val YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/"

    /**
     * *REQUIRED PARAMETERS
     **/
    // Part Parameters
    const val CONTENT_DETAILS ="contentDetails"
    const val SNIPPET = "snippet" // contains channelId, title, description, tags, categoryId
    const val STATISTICS = "statistics" //Gives the video views number as statistics

    // CHART Parameters
    const val MOST_POPULAR_VIDEOS = "mostPopular" // based on the specified region eg. Kenya, TZ, UG...
    const val REGION_CODE = "KE"
    const val YOUTUBE_API_KEY = "AIzaSyAv_oO_G5E4rPu8WpSapzVR3sqHESPwdzg"// PUT YOUR OWN API KEY CREDENTIAL

}