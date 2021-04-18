package com.lws.lwsmediaplayer.data

import com.lws.lwsmediaplayer.data.api.ApiService
import com.lws.lwsmediaplayer.data.model.ItunesResponse
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: ApiService): SafeApiRequest() {

    suspend fun fetchList(keyword: String): ItunesResponse {
        return apiRequest { api.getListFromSearch(keyword) }
    }
}