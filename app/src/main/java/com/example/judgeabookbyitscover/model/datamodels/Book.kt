package com.example.judgeabookbyitscover.model.datamodels

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookshelf")
data class Book(
    val amazon_product_url: String = "test",
    val author: String = "test",
    @PrimaryKey @NonNull val book_image: String = "test",
    val description: String = "test",
    val primary_isbn13: String = "test",
    val rank: Int = -1,
    val rank_last_week: Int = -1,
    val title: String = "test",
    val weeks_on_list: Int = -1
)
