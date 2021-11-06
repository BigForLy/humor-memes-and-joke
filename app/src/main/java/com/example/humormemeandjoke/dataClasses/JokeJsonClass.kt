package com.example.humormemeandjoke.dataClasses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JokeJsonClass(
    @SerialName("content")
    val content: String?
){
    fun getString() : String{
        return content!!
    }
}