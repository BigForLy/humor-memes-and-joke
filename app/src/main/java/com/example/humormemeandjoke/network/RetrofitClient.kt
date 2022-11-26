package com.example.humormemeandjoke.network


object RetrofitClient {

    var url_mapping: String = ""

    fun get_joke(): String {
        return url_mapping + "joke"
    }


    fun get_stories(): String {
        return url_mapping + "stories"
    }

    fun get_status(): String {
        return url_mapping + "status"
    }

}