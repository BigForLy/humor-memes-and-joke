package com.example.humormemeandjoke.network

import okhttp3.Request

object RetrofitClient {
    private val retrofitRzhu by lazy {
        Request.Builder()
            .url("http://rzhunemogu.ru/RandJSON.aspx?CType=1")
            .get()
            .build()
    }

    val retrofit: Request by lazy { retrofitRzhu }
}