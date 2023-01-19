package com.steve_md.youtubeapp.di

import com.steve_md.youtubeapp.data.network.api.ApiService
import com.steve_md.youtubeapp.util.Constants.YOUTUBE_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(YOUTUBE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(loggingInterceptor())
            .build()

    }

     private fun loggingInterceptor(): OkHttpClient {
         val loggingInterceptor = HttpLoggingInterceptor()
             .setLevel(HttpLoggingInterceptor.Level.BASIC)
         return OkHttpClient.Builder()
             .addInterceptor(loggingInterceptor)
             .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }
}