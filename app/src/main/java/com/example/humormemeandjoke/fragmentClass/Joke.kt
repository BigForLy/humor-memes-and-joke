package com.example.humormemeandjoke.fragmentClass

import com.example.humormemeandjoke.adapter.RequestClassAdapter
import com.example.humormemeandjoke.network.RetrofitClient

class Joke : RequestClassAdapter {
    override fun get_url(): String {
        return RetrofitClient.get_joke()
    }
}