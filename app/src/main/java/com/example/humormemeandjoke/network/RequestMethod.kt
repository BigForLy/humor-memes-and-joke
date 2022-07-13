package com.example.humormemeandjoke.network

import com.example.humormemeandjoke.dataClasses.JokeJsonClass
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import java.io.IOException

object RequestMethod {
    private val client : OkHttpClient  by lazy { OkHttpClient() }

    @ExperimentalSerializationApi
    fun request(request: Request, listener: ((xes: String)->Unit)? = null){

        client.newCall(request).enqueue(object : Callback {
            // При возникновении ошибки подключении
            override fun onFailure(call: Call, e: IOException) {
                println(e.message.toString())
            }

            // При приходе запроса, производим обработку сообщения
            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                var result = ""

                try {
                    val commonBlock = Json.decodeFromString<JokeJsonClass>(json ?: "{\"content\":\"\"}")
                    result = commonBlock.content.toString()
                }
                catch(e: Exception){
                    println(e.message.toString())
                }

                listener?.invoke(result)
            }
        })
    }
}