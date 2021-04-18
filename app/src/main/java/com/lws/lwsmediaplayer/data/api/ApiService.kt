package com.lws.lwsmediaplayer.data.api

import com.lws.lwsmediaplayer.data.model.ItunesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search?attribut=music&limit=12")
    suspend fun getListFromSearch(
        @Query("term") keyword: String
    ): Response<ItunesResponse>

}