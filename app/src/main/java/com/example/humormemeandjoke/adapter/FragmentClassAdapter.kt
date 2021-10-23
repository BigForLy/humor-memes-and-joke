package com.example.humormemeandjoke.adapter

import androidx.fragment.app.Fragment

interface FragmentClassAdapter {
    var joke: String

    fun initialFrame(fragment: Fragment)
}