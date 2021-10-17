package com.example.humormemeandjoke

import com.example.humormemeandjoke.adapter.RequestClassAdapter
import com.example.humormemeandjoke.dataClasses.TronaldDumpJsonClass
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import java.io.IOException

@Suppress("NAME_SHADOWING")
class RequestMethod {
    private val client : OkHttpClient = OkHttpClient()

    @ExperimentalSerializationApi
    fun request(requestClass: RequestClassAdapter, listener: ((xes: String)->Unit)? = null){
        val host = requestClass.host
        val key = requestClass.key
        val request = Request.Builder()
            .url("https://matchilling-tronald-dump-v1.p.rapidapi.com/random/quote")
            .get()
            .addHeader("accept", "application/hal+json")
            .addHeader("x-rapidapi-host", host)
            .addHeader("x-rapidapi-key", key)
            .build()

        client.newCall(request).enqueue(object : Callback {
            // При возникновении ошибки подключении
            override fun onFailure(call: Call?, e: IOException) {
                println(e.message.toString())
            }

            // При приходе запроса, производим обработку сообщения
            override fun onResponse(call: Call?, response: Response?) {
                val json = response?.body()?.string()
                var notNullJson = ""
                var result = ""

                if (json != null) {
                    notNullJson = json.replace("\r\n", "")
                }
                try {
                    val commonBlock = Json.decodeFromString<TronaldDumpJsonClass>(notNullJson)
                    result = commonBlock.toParse()
                }
                catch(e: Exception){
                    println(e.message.toString())
                }
//                listener?.invoke(notNullJson)
                listener?.invoke(result)
            }
        })
    }
}