package com.example.moviestar.model

import com.example.moviestar.databinding.DetailFragmentBinding
import com.example.moviestar.view.DetailFragment
import com.example.moviestar.view.MainAdapter

interface LocalRepository {

    fun getAllHistory(): List<Movie>

    fun getOneMovieNote(id: Int): Movie

    fun saveEntity(movie: Movie)

    fun updateNote(id: Int, note: String)

    fun updateTimestamp(timestamp: Long, id: Int)

}