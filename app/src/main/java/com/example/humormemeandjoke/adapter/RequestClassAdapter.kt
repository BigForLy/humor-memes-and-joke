package com.example.humormemeandjoke.adapter

import com.example.humormemeandjoke.RequestMethodNew

interface RequestClassAdapter {
    var host: String
    var key: String
    var dataClass: Any
    val accept: String
}