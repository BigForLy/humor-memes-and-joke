package com.example.humormemeandjoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.humormemeandjoke.adapter.FragmentClassAdapter
import com.example.humormemeandjoke.fragmentClass.RzhuNeMogu
import com.example.humormemeandjoke.network.RetrofitClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.humormemeandjoke.ui.one.OneFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class StartHumorActivity : AppCompatActivity() {

    private val requestMethodNew = RequestMethodNew
    private val fragmentMap : MutableMap<Int, Fragment> = mutableMapOf()

    @ExperimentalSerializationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_humor)

        fragmentMap[R.id.ic_dashboard] = OneFragment()
        replaceFragment(initialFragment(R.id.ic_dashboard))

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_dashboard ->  replaceFragment(initialFragment(R.id.ic_dashboard))
                R.id.ic_settings -> replaceNewFragment(RzhuNeMogu(), initialFragment(R.id.ic_settings))
                R.id.ic_info ->  replaceNewFragment(RzhuNeMogu(), initialFragment(R.id.ic_info))
            }
            true
        }
        bottomNavigation.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.ic_dashboard -> replaceFragment(initialFragment(R.id.ic_dashboard))
                R.id.ic_settings -> replaceFragment(initialFragment(R.id.ic_settings))
                R.id.ic_info -> replaceFragment(initialFragment(R.id.ic_info))
            }
        }
    }

    private fun initialFragment(id: Int) : Fragment {
        return if (fragmentMap.contains(id)) {
            fragmentMap[id]!!
        } else {
            fragmentMap[id] = OneFragment.newInstance("", RetrofitClient.retrofit)
            fragmentMap[id]!!
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    @ExperimentalSerializationApi
    private fun replaceNewFragment(fragmentClass: FragmentClassAdapter, actualFragment: Fragment) {
        replaceFragment(actualFragment)

        GlobalScope.launch(Dispatchers.Main) {
            requestMethodNew.request(RetrofitClient.retrofit) {
                runOnUiThread {
                    println(it)
                    fragmentClass.joke = it
                    fragmentClass.initialFrame(actualFragment)
                }
            }
        }
    }

}