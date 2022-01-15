package com.example.moviestar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int = 0,
    val title: String = "",
    val releaseYear: String = "",
    val voteAverage: Double = 0.0,
    val tagline: String = "",
    val overview: String = "",
    val movieImage: String = "",
    val adult: Boolean = false,
    val runtime: Int = 0,
    var note: String = ""): Parcelable

fun getWorldMovies(): List<Movie> {
    return listOf(
        Movie(550, "Fight Club", "1999", 8.4),
        Movie(551, "The Poseidon Adventure", "1972", 7.2),
        Movie(552, "Bread and Tulips", "2000", 7.3),
        Movie(553, "Dogville", "2003", 7.8),
        Movie(555, "Absolut", "2005", 7.9),
    )
}

fun getRussianMovies(): List<Movie> {
    return listOf(
        Movie(554, "The Cuckoo", "2002", 7.2),
        Movie(457842,"Arrhythmia", "2017", 7.3),
        Movie(577040, "100 Minutes", "2021", 8.0),
        Movie(633882, "A Man From Podolsk", "2020", 7.3)
    )
}