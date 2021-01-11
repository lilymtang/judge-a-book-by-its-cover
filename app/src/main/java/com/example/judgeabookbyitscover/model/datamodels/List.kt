package com.example.judgeabookbyitscover.model.datamodels

import kotlin.collections.List

data class List(
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