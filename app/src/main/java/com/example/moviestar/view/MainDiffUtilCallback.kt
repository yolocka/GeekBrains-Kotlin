package com.example.moviestar.view

import androidx.recyclerview.widget.DiffUtil
import com.example.moviestar.model.Movie

class MainDiffUtilCallback (private val oldList: List<Movie>, private val newList: List<Movie>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie: Movie = oldList[oldItemPosition]
        val newMovie: Movie = newList[newItemPosition]
        return oldMovie == newMovie
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie: Movie = oldList[oldItemPosition]
        val newMovie: Movie = newList[newItemPosition]
        return (oldMovie.title == newMovie.title
                && oldMovie.releaseYear == newMovie.releaseYear
                && oldMovie.voteAverage == newMovie.voteAverage)
    }
}