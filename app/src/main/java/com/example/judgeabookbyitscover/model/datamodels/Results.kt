package com.example.judgeabookbyitscover.model.datamodels

data class Results(
        val bestsellers_date: String,
        val lists: kotlin.collections.List<List>,
        val next_published_date: String,
        val previous_published_date: String,
        val published_date: String,
        val published_date_description: String
)