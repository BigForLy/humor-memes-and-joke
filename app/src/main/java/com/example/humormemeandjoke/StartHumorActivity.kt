package com.example.humormemeandjoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.humormemeandjoke.adapter.FragmentClassAdapter
import com.example.humormemeandjoke.adapter.RequestClassAdapter
import com.example.humormemeandjoke.fragmentClass.TronaldDumpFragmentClass
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.humormemeandjoke.ui.one.OneFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import me.kondratyev.androidApp.ui.two.TwoFragment

class StartHumorActivity : AppCompatActivity() {

    private val oneFragment = OneFragment()
    private val twoFragment = TwoFragment()

    private val requestMethod = RequestMethod()

    @ExperimentalSerializationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_humor)

        replaceFragment(oneFragment)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_dashboard -> replaceFragment(oneFragment)
                R.id.ic_settings -> replaceFragment(twoFragment)
                R.id.ic_info -> replaceNewFragment(TronaldDumpFragmentClass())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    @ExperimentalSerializationApi
    private fun replaceNewFragment(fragmentClass: FragmentClassAdapter) {
        val actualFragment = oneFragment
        replaceFragment(actualFragment)
        GlobalScope.launch(Dispatchers.Main) {
            requestMethod.request(fragmentClass as RequestClassAdapter) {
                runOnUiThread {
                    println(it)
                    fragmentClass.joke = it
                    fragmentClass.initialFrame(actualFragment)
                }
            }
        }
    }

}