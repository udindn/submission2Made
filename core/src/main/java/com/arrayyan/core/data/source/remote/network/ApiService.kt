package com.arrayyan.core.data.source.remote.network

import com.arrayyan.core.data.source.remote.response.ListContentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovieList(
        @Query("api_key") apiKey : String,
        @Query("language") language : String
    ): ListContentResponse

    @GET("discover/tv")
    suspend fun getTvList(
        @Query("api_key") apiKey : String,
        @Query("language") language : String
    ): ListContentResponse
}