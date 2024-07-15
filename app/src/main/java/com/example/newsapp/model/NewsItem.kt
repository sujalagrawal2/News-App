package com.example.newsapp.model

data class NewsItem(
    val title:String,
    val description:String,
    val content:String,
    val url:String,
    val image:String,
    val publishedAt:String,
    val source:Source
)
