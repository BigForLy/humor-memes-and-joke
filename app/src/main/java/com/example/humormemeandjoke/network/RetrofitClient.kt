package com.example.humormemeandjoke.network

import okhttp3.Request

object RetrofitClient {
    private val retrofitJokeRequest by lazy {
        Request.Builder()
            .url("https://humor-memes-and-joke.herokuapp.com/joke")
            .get()
            .build()
    }

    private val retrofitStoriesRequest by lazy {
        Request.Builder()
            .url("https://humor-memes-and-joke.herokuapp.com/stories")
            .get()
            .build()
    }

    private val retrofitStatusRequest by lazy {
        Request.Builder()
            .url("https://humor-memes-and-joke.herokuapp.com/status")
            .get()
            .build()
    }

    val retrofitJoke: Request by lazy { this.retrofitJokeRequest }
    val retrofitStories: Request by lazy { this.retrofitStoriesRequest }
    val retrofitStatus: Request by lazy { this.retrofitStatusRequest }
}