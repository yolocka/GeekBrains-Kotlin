package com.example.moviestar.model

import android.app.IntentService
import android.content.Intent

class MainIntentService : IntentService("MainIntentService") {

    companion object {
        const val TAG = "MainIntentService"
    }
    override fun onHandleIntent(intent: Intent?) {
        intent?.getParcelableExtra<Movie>("MOVIE_EXTRA")?.let { movie ->
            MovieLoader.load(movie, object: MovieLoader.OnMovieLoadedListener {
                override fun onLoaded(movieDTO: MovieDTO) {
                    applicationContext.sendBroadcast(Intent(applicationContext, MainReceiver::class.java).apply {
                        action = MainReceiver.MOVIE_LOAD_SUCCESS
                        putExtra("MOVIE_EXTRA", Movie(
                            id = movieDTO.id ?: 0,
                            title = movieDTO.title ?: "",
                            releaseYear = movieDTO.releaseDate.toString().takeLast(4) ?: "",
                            voteAverage = movieDTO.voteAverage ?: 0.0,
                            tagline = movieDTO.tagline ?: "",
                            overview = movieDTO.overview ?: ""
                        ))
                    })
                }

                override fun onFailed(throwable: Throwable) {
                    applicationContext.sendBroadcast(Intent(applicationContext, MainReceiver::class.java).apply {
                        action = MainReceiver.MOVIE_LOAD_FAILED
                    })
                }

            })
        }
    }


}