package com.example.moviestar.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviestar.R
import com.example.moviestar.databinding.ActivityMainBinding
import com.example.moviestar.model.MainReceiver

class MainActivity : AppCompatActivity() {

    private val receiver = MainReceiver()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, MainFragment.newInstance())
            .commit()

        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}