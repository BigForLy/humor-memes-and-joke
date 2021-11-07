package com.example.humormemeandjoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.humormemeandjoke.adapter.RequestClassAdapter
import com.example.humormemeandjoke.fragmentClass.Joke
import com.example.humormemeandjoke.fragmentClass.Status
import com.example.humormemeandjoke.fragmentClass.Stories
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.humormemeandjoke.ui.one.OneFragment

class StartHumorActivity : AppCompatActivity() {

    private val fragmentMap : MutableMap<Int, Fragment> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_humor)

        replaceFragment(Joke(), R.id.joke)

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

    private fun replaceFragment(fragmentClass: RequestClassAdapter, id: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        for (i in fragmentMap.iterator()) {
            transaction.hide(i.value)
        }
        if (!fragmentMap.contains(id))  {
            fragmentMap[id] = OneFragment.newInstance("", fragmentClass.retrofit)
            transaction.add(R.id.fragment_container, fragmentMap[id]!!)
        }
        transaction.show(fragmentMap[id]!!).commit()
    }
}