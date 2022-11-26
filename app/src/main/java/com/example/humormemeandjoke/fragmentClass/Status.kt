package com.example.humormemeandjoke.fragmentClass

import com.example.humormemeandjoke.adapter.RequestClassAdapter
import com.example.humormemeandjoke.network.RetrofitClient


class Status : RequestClassAdapter {
    override fun get_url(): String {
        return RetrofitClient.get_status()
    }
}