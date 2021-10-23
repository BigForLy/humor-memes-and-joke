package com.example.humormemeandjoke

import com.example.humormemeandjoke.dataClasses.UniversalJsonClass
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import java.io.IOException

object RequestMethodNew {
    private val client : OkHttpClient  by lazy { OkHttpClient() }

    @ExperimentalSerializationApi
    fun request(request: Request, listener: ((xes: String)->Unit)? = null){

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
                    notNullJson = json.replace("\r\n-", "|||")
                        .replace("\r\n", "")
                        .replace("|||", "\r\n-")
                }
                try {
                    val commonBlock = Json.decodeFromString<UniversalJsonClass>(notNullJson)
                    result = commonBlock.toRzhu()
                }
                catch(e: Exception){
                    println(e.message.toString())
                }

                listener?.invoke(result)
            }
        })
    }
}