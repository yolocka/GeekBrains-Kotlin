package com.example.moviestar.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieDTO (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("release_date")
    val releaseDate: Date,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("genres")
    val genres: List<Genre>
)

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)