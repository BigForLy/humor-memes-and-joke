package com.example.humormemeandjoke.dataClasses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MappingJsonClass(
    @SerialName("mapping")
    val mapping: String
){
}