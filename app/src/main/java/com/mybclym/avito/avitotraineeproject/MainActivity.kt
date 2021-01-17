package com.mybclym.avito.avitotraineeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            initFragment()
        }
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ItemsListFragment(), TAG_FRAGMENT_LIST)
            .commit()
    }

    companion object {
        const val TAG_FRAGMENT_LIST = "ListFragment"
    }

}