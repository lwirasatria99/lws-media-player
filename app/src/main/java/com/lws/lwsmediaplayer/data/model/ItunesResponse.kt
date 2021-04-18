package com.lws.lwsmediaplayer.data.model

data class ItunesResponse(
    val resultCount: Int,
    val results: List<ResultItunes>?
)