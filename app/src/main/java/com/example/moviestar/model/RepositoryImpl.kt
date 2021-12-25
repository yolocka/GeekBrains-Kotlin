package com.example.moviestar.model

class RepositoryImpl : Repository {
    override fun getMovieFromServer(): Movie {
        return Movie("Secret Beyond the Door", 1947, 6.5)
    }

    override fun getMovieFromLocalStorage(): Movie {
        return Movie()
    }
}