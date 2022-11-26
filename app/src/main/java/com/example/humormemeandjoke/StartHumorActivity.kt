package com.example.humormemeandjoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.humormemeandjoke.adapter.RequestClassAdapter
import com.example.humormemeandjoke.dataClasses.MappingJsonClass
import com.example.humormemeandjoke.fragmentClass.Joke
import com.example.humormemeandjoke.fragmentClass.Status
import com.example.humormemeandjoke.fragmentClass.Stories
import com.example.humormemeandjoke.network.RetrofitClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.humormemeandjoke.ui.one.OneFragment
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import okio.IOException

class StartHumorActivity : AppCompatActivity() {

    private val fragmentMap : MutableMap<Int, Fragment> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_humor)

        run()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.joke ->  replaceFragment(Joke(), R.id.joke)
                R.id.stories -> replaceFragment(Stories(), R.id.stories)
                R.id.status ->  replaceFragment(Status(), R.id.status)
            }
            true
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun run() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://bigforly.github.io/humor-index/")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()

                replaceFragment(Joke(), R.id.joke)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    try {
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")

                        val body = response.body!!.string()
                        val commonBlock = Json.decodeFromString<MappingJsonClass>(body)

                        RetrofitClient.url_mapping = commonBlock.mapping
                    } catch (e: Exception) {
                        println(e.message.toString())
                    }

                    replaceFragment(Joke(), R.id.joke)
                }
            }
        })
    }

    private fun replaceFragment(fragmentClass: RequestClassAdapter, id: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        for (i in fragmentMap.iterator()) {
            transaction.hide(i.value)
        }
        if (!fragmentMap.contains(id))  {
            fragmentMap[id] = OneFragment.newInstance("", fragmentClass.get_url())
            transaction.add(R.id.fragment_container, fragmentMap[id]!!)
        }
        transaction.show(fragmentMap[id]!!).commit()
    }
}