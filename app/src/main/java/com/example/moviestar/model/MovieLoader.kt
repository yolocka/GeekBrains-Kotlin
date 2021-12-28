package com.example.moviestar.model

import android.os.Build
import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors

object MovieLoader {

    private const val MY_APY_KEY = "1c945bad114a0830195ca7fbd2922850"

    fun load (movie: Movie, listener: OnMovieLoadedListener) {

            var urlConnection: HttpURLConnection? = null

            try {
                val uri = URL("https://api.themoviedb.org/3/movie/${movie.id}?api_key=${MY_APY_KEY}")
                urlConnection = uri.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 1000
                urlConnection.connectTimeout = 1000

                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))

                val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
                    reader.lines().collect(Collectors.joining("\n"))
                } else {
                    ""
                }

                val movieDTO = Gson().fromJson(result, MovieDTO::class.java)
                listener.onLoaded(movieDTO = movieDTO)
            } catch (e: Exception) {
                listener.onFailed(e)
            } finally {
                urlConnection?.disconnect()
            }
    }

    interface OnMovieLoadedListener{
        fun onLoaded(movieDTO: MovieDTO)
        fun onFailed(throwable: Throwable)
    }
}
