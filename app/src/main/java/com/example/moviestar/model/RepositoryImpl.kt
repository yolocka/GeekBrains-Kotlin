package com.example.moviestar.model

class RepositoryImpl : Repository {
    override fun getMovieFromServer(): Movie {
        return Movie("Fight Club", 1999, 8.4, listOf("Drama") , "Mischief. Mayhem. Soap.", "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \\\"fight clubs\\\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.")
    }

    override fun getMovieFromLocalStorageRus(): List<Movie> {
        return getRussianMovies()
    }

    override fun getMovieFromLocalStorageWorld(): List<Movie> {
        return getWorldMovies()
    }
}