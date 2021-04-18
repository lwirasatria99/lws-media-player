package com.lws.lwsmediaplayer.data.model

data class ResultItunes(
    val artistName: String,
    val artworkUrl100: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val collectionName: String,
    val previewUrl: String,
    val trackName: String
) {

    constructor() : this("",
    "",
    "",
    "",
    "",
    "",
    "")

    var isPlaying = false
    var audioSessionId = -1
}