package com.example.moviestar.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.moviestar.R
import com.example.moviestar.databinding.ActivityMainBinding
import com.example.moviestar.model.MainReceiver
class MainActivity : AppCompatActivity() {

    private val receiver = MainReceiver()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private val permissionResult =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            val fineLocationGranted = result.getOrDefault(
                Manifest.permission.ACCESS_FINE_LOCATION, false
            )
            val coarseLocationGranted = result.getOrDefault(
                Manifest.permission.ACCESS_COARSE_LOCATION, false
            )
            when {
                fineLocationGranted or coarseLocationGranted -> showLocation()
                ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) -> AlertDialog.Builder(this).setTitle(R.string.ask_contacts_permission)
                    .setMessage(R.string.again_ask_contacts_permission)
                    .setPositiveButton(R.string.give_permission) { _, _ -> requestPermission() }
                    .setNegativeButton(R.string.do_not_give_permission) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()
                else -> requestPermission()
            }
        }


    private fun showLocation() {
        this.startActivity(Intent(this, MapsActivity::class.java))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun requestPermission() {
        permissionResult.launch(
            arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
            ))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, MainFragment.newInstance())
            .commit()

        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.contacts) {
            startActivity(Intent(this, ContactsActivity::class.java))
            return true
        } else if (item.itemId == R.id.menu_google_maps) {
            requestPermission()
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