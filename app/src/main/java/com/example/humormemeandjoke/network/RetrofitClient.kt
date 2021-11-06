package com.example.humormemeandjoke.network

import okhttp3.Request

object RetrofitClient {
    private val retrofitJokeRequest by lazy {
        Request.Builder()
            .url("http://rzhunemogu.ru/RandJSON.aspx?CType=1")
            .get()
            .build()
    }

    private val retrofitStoriesRequest by lazy {
        Request.Builder()
            .url("http://rzhunemogu.ru/RandJSON.aspx?CType=2")
            .get()
            .build()
    }

    val retrofitJoke: Request by lazy { this.retrofitJokeRequest }
    val retrofitStories: Request by lazy { this.retrofitStoriesRequest }
}