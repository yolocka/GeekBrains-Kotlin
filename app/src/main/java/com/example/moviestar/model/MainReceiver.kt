package com.example.moviestar.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.widget.Toast
import com.example.moviestar.R

class MainReceiver : BroadcastReceiver() {

    companion object {
        const val MOVIE_LOAD_SUCCESS = "MOVIE_LOAD_SUCCESS"
        const val MOVIE_LOAD_FAILED = "MOVIE_LOAD_FAILED"
    }

    override fun onReceive(context: Context, intent: Intent) {

        when (intent.action) {
            MOVIE_LOAD_SUCCESS -> RepositoryImpl.movieLoaded(intent.extras?.getParcelable<Movie>("MOVIE_EXTRA"))
            MOVIE_LOAD_FAILED -> RepositoryImpl.movieLoaded(null)
            CONNECTIVITY_ACTION ->  Toast.makeText(context, R.string.con_changed, Toast.LENGTH_LONG).show()
        }
    }
}

