package com.example.newsapp.retrofit

import com.example.newsapp.api.NewsService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val BASE_URL = "https://gnews.io/api/"
    val api: NewsService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(NewsService::class.java)
    }
}