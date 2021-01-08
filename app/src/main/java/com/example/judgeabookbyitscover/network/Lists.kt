package com.example.judgeabookbyitscover.network

import com.example.judgeabookbyitscover.network.Book

data class Lists(
    val books: List<Book>,
    val display_name: String,
    val list_id: Int,
    val list_image: String,
    val list_image_height: Int,
    val list_image_width: Int,
    val list_name: String,
    val list_name_encoded: String,
    val updated: String
)