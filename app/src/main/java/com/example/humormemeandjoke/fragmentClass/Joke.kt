package com.example.humormemeandjoke.fragmentClass

import com.example.humormemeandjoke.adapter.RequestClassAdapter
import com.example.humormemeandjoke.network.RetrofitClient
import okhttp3.Request

class Joke : RequestClassAdapter {
    override var retrofit: Request = RetrofitClient.retrofitJoke
}