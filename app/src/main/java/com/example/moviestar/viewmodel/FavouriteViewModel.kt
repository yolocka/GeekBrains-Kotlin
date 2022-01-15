package com.example.moviestar.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviestar.model.LocalRepository
import com.example.moviestar.model.LocalRepositoryImpl
import com.example.moviestar.model.Movie
import com.example.moviestar.view.App

class FavouriteViewModel : ViewModel() {

    private val localRepo: LocalRepository = LocalRepositoryImpl(App.getHistoryDao())


    fun getAllHistory(): List<Movie> {
        return localRepo.getAllHistory()
    }

}