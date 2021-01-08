package com.example.judgeabookbyitscover.network

data class Volume(
    val id: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)