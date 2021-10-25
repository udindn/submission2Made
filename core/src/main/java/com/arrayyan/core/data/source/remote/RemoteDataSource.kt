package com.arrayyan.core.data.source.remote

import android.util.Log
import com.arrayyan.core.data.source.remote.network.ApiResponse
import com.arrayyan.core.data.source.remote.network.ApiService
import com.arrayyan.core.data.source.remote.response.ContentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    companion object {
        private const val API_KEY = "90d843f3e9f19e433814b0f501b4eace"
        private const val LANGUAGE = "en-US"
    }

    suspend fun getAllMovies(): Flow<ApiResponse<List<ContentResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getMovieList(API_KEY, LANGUAGE)
                val dataArray = response.result
                if (dataArray!!.isNotEmpty()){
                    emit(ApiResponse.Success(response.result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTvShow(): Flow<ApiResponse<List<ContentResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getTvList(API_KEY, LANGUAGE)
                val dataArray = response.result
                if (dataArray!!.isNotEmpty()){
                    emit(ApiResponse.Success(response.result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}