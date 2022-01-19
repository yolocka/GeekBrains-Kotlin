package com.example.moviestar.view

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.contacts) {
            startActivity(Intent(this, ContactsActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}