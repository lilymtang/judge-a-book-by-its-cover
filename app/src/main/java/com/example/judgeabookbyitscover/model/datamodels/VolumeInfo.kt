package com.example.judgeabookbyitscover.model.datamodels

import kotlin.collections.List

data class VolumeInfo(
        val authors: List<String>,
        val averageRating: Double,
        val categories: List<String>,
        val description: String,
        val imageLinks: ImageLinks,
        val industryIdentifiers: List<IndustryIdentifier>,
        val pageCount: Int,
        val previewLink: String,
        val printedPageCount: Int,
        val publishedDate: String,
        val publisher: String,
        val ratingsCount: Int,
        val title: String
)