package com.example.humormemeandjoke.network


object RetrofitClient {

    var url_mapping: String = ""

    fun get_joke(): String {
        return url_mapping + "api/v2/joke"
    }


    fun get_stories(): String {
        return url_mapping + "api/v2/stories"
    }

    fun get_status(): String {
        return url_mapping + "api/v2/status"
    }

}