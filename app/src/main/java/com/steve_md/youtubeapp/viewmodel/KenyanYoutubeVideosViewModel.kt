package com.steve_md.youtubeapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steve_md.youtubeapp.data.dto.YoutubeResponse
import com.steve_md.youtubeapp.data.repository.GetKenyanPopularYoutubeVideosRepository
import com.steve_md.youtubeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class KenyanYoutubeVideosViewModel @Inject constructor(
    private val youtubeVideosRepository: GetKenyanPopularYoutubeVideosRepository
) : ViewModel(){

    private val _kenyanPopularYoutubeVideos : MutableLiveData<Resource<YoutubeResponse>> = MutableLiveData()
    val kenyanYoutubeVideosViewModel:LiveData<Resource<YoutubeResponse>>
    get() = _kenyanPopularYoutubeVideos

    init {
        getKenyanPopularVideos()
    }

    private fun getKenyanPopularVideos() = viewModelScope.launch {
        _kenyanPopularYoutubeVideos.postValue(Resource.Loading())
        val youtubeResponse = youtubeVideosRepository.getKenyanPopularYoutubeVideos()
        _kenyanPopularYoutubeVideos.postValue(checkYoutubeResponse(youtubeResponse))
    }

    private fun checkYoutubeResponse(youtubeResponse: Response<YoutubeResponse>): Resource<YoutubeResponse> {
        if (youtubeResponse.isSuccessful) {
            Timber.d("Successfully fetched data")
            youtubeResponse.body()?.let { youtubeResponse ->
                return  Resource.Success(youtubeResponse)
            }
        }
        Timber.e("An unexpected error occurred.")
         return Resource.Error(null, "Error occurred")
    }
}