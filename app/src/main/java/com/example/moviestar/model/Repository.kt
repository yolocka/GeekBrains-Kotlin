package com.example.moviestar.model

interface Repository {
    fun getMovieFromServer(): Movie?
    fun getMovieFromLocalStorageRus(): List<Movie>
    fun getMovieFromLocalStorageWorld(): List<Movie>
    fun movieLoaded(movie: Movie?)
    fun addLoaderListener(listener: OnLoadListener)
    fun removeLoadListener(listener: OnLoadListener)
    fun interface OnLoadListener{
        fun onLoaded()
    }
}