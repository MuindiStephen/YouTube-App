package com.steve_md.youtubeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.steve_md.youtubeapp.data.repository.GetKenyanPopularYoutubeVideosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class KenyanYoutubeVideosViewModel @Inject constructor(
    youtubeVideosRepository: GetKenyanPopularYoutubeVideosRepository
) : ViewModel(){

    /*
     Gets flow of PagingData from Repository
     and caches the PagingData through the viewModel
     and returns livedata to collect the latest data from PagingData
     */

    val kenyanYoutubeVideosViewModel = youtubeVideosRepository.getYoutubeVideos()
        .cachedIn(viewModelScope).asLiveData()
        //.flow.cachedIn(viewModelScope).asLiveData()



    /*
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
                return Resource.Success(youtubeResponse)
            }
        }
        Timber.e("An unexpected error occurred!")
         return Resource.Error(null, "Error occurred")
    }

     */
}