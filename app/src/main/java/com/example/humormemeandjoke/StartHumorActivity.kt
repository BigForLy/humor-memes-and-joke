package com.example.humormemeandjoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.humormemeandjoke.adapter.RequestClassAdapter
import com.example.humormemeandjoke.fragmentClass.Joke
import com.example.humormemeandjoke.fragmentClass.Stories
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.humormemeandjoke.ui.one.OneFragment

class StartHumorActivity : AppCompatActivity() {

    private val fragmentMap : MutableMap<Int, Fragment> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_humor)

        replaceFragment(Joke(), R.id.ic_dashboard)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_dashboard ->  replaceFragment(Joke(), R.id.ic_dashboard)
                R.id.ic_settings -> replaceFragment(Stories(), R.id.ic_settings)
                R.id.ic_info ->  replaceFragment(Stories(), R.id.ic_info)
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