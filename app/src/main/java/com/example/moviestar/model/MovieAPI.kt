package com.example.moviestar.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.net.HttpURLConnection
import java.net.URL

interface MovieAPI {


    @GET("3/movie/{id}")
    fun getMovie (
        @Path("id")  id: Int,
        @Query("api_key") api_key: String
    ): Call<MovieDTO>
}