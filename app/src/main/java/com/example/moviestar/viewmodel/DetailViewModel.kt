package com.example.moviestar.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviestar.model.LocalRepository
import com.example.moviestar.model.LocalRepositoryImpl
import com.example.moviestar.model.Movie
import com.example.moviestar.view.App

class DetailViewModel : ViewModel() {

    private val localRepo: LocalRepository = LocalRepositoryImpl(App.getHistoryDao())

    fun saveHistory(movie: Movie) {
        localRepo.saveEntity(movie)
    }

    fun updateNote(id: Int, note: String) {
        localRepo.updateNote(id, note)
    }

    fun updateTimestamp(timestamp: Long, id: Int) {
        localRepo.updateTimestamp(timestamp, id)
    }

    fun getOneMovieHistory(id: Int) : Movie{
        return localRepo.getOneMovieNote(id)
    }

}