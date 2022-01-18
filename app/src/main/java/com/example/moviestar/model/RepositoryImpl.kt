package com.example.moviestar.model

object RepositoryImpl : Repository {

    private val listeners: MutableList<Repository.OnLoadListener> = mutableListOf()
    private var movie: Movie? = null

    override fun getMovieFromServer(): Movie? = movie

    override fun getMovieFromLocalStorageRus(): List<Movie> {
        return getRussianMovies()
    }

    override fun getMovieFromLocalStorageWorld(): List<Movie> {
        return getWorldMovies()
    }

    override fun movieLoaded(movie: Movie?) {
        this.movie = movie

        listeners.forEach{it.onLoaded()}
    }

    override fun addLoaderListener(listener: Repository.OnLoadListener) {
        listeners.add(listener)
    }

    override fun removeLoadListener(listener: Repository.OnLoadListener) {
        listeners.remove(listener)
    }
}