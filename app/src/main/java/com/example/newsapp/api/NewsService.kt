package com.example.newsapp.api

import com.example.newsapp.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v4/top-headlines?apikey=1bf3c4ff70752e72a5c0ec9fdcd5941e")
    suspend fun getNewsItems(
        @Query("category") category: String,
        @Query("country") country: String,
        @Query("lang") language:String,
        @Query("max") max: Int,
        @Query("q") search: String
    ): Response<News>
}