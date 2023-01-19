package com.steve_md.youtubeapp.viewmodel

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
import javax.inject.Inject


@HiltViewModel
class KenyanYoutubeVideosViewModel @Inject constructor(
    private val youtubeVideosRepository: GetKenyanPopularYoutubeVideosRepository
) : ViewModel(){

    private val _kenyanPopularYoutubeVideos : MutableLiveData<Resource<YoutubeResponse>> = MutableLiveData()
    val kenyanYoutubeVideosViewModel:LiveData<Resource<YoutubeResponse>>
    get() = _kenyanPopularYoutubeVideos

    private fun getKenyanPopularVideos() = viewModelScope.launch {
        _kenyanPopularYoutubeVideos.postValue(Resource.Loading())
        val youtubeResponse = youtubeVideosRepository.getKenyanPopularYoutubeVideos()
        _kenyanPopularYoutubeVideos.postValue(checkYoutubeResponse(youtubeResponse))
    }

    private fun checkYoutubeResponse(youtubeResponse: Response<YoutubeResponse>): Resource<YoutubeResponse> {
        if (youtubeResponse.isSuccessful) {
            youtubeResponse.body()?.let { youtubeResponse ->
                return Resource.Success(youtubeResponse)
            }
        }
         return Resource.Error(null, "Error occurred")
    }
}