package com.example.moviestar.model

import android.os.Build
import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors

object MovieLoader {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .callTimeout(1000, TimeUnit.MILLISECONDS)
        .connectTimeout(1000, TimeUnit.MILLISECONDS)
        .build()

    private val movieAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
        .create(MovieAPI::class.java)

    private const val MY_APY_KEY = "1c945bad114a0830195ca7fbd2922850"

    fun loadRetrofit(movie: Movie, listener: OnMovieLoadedListener) {
        movieAPI.getMovie(movie.id, MY_APY_KEY)
            .enqueue(object: Callback<MovieDTO> {
                override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
                    if (response.isSuccessful) {
                        response.body()?.let { listener.onLoaded(it) }
                    } else {
                        listener.onFailed(Exception(response.message()))
                    }
                }

                override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
                    listener.onFailed(t)
                }

            })
    }

    interface OnMovieLoadedListener{
        fun onLoaded(movieDTO: MovieDTO)
        fun onFailed(throwable: Throwable)
    }
}
