package com.example.humormemeandjoke.fragmentClass

import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.humormemeandjoke.R
import com.example.humormemeandjoke.adapter.FragmentClassAdapter
import com.example.humormemeandjoke.adapter.RequestClassAdapter

class TronaldDumpFragmentClass() : FragmentClassAdapter, RequestClassAdapter {
//    var joke = ""
    override var joke: String = ""
    override var host: String = "matchilling-tronald-dump-v1.p.rapidapi.com"
    override var key: String = "1d75bf0d91msh7c85e84ee171511p1dc8f7jsnff229aec2e8d"

    override fun initialFrame(fragment: Fragment) {
        val textView = fragment.view?.findViewById<TextView>(R.id.text_view_fragment_one)
        textView!!.text = joke
    }
}