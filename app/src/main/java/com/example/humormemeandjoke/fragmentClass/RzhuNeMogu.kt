package com.example.humormemeandjoke.fragmentClass

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.humormemeandjoke.R
import com.example.humormemeandjoke.adapter.FragmentClassAdapter
import com.example.humormemeandjoke.adapter.RequestClassAdapter
import com.example.humormemeandjoke.dataClasses.TronaldDumpJsonClass
import com.example.humormemeandjoke.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import java.util.*

class RzhuNeMogu : FragmentClassAdapter, RequestClassAdapter {
    override var dataClass : Any = TronaldDumpJsonClass
    override var joke: String = ""
    override var host: String = ""
    override var key: String = ""
    override var accept: String = ""

    private lateinit var view: View

    override fun initialFrame(fragment: Fragment) {
        view = fragment.requireView()
        val textView = view.findViewById<TextView>(R.id.text_view_fragment_one)
        textView!!.text = joke
    }

}