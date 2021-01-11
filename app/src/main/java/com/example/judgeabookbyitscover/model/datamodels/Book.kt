package com.example.judgeabookbyitscover.model.datamodels

data class Book(
    val amazon_product_url: String,
    val author: String,
    val book_image: String,
    val book_image_height: Int,
    val book_image_width: Int,
    val description: String,
    val primary_isbn10: String,
    val primary_isbn13: String,
    val rank: Int,
    val rank_last_week: Int,
    val title: String,
    val weeks_on_list: Int
)
