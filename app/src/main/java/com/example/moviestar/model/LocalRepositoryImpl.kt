package com.example.moviestar.model

import android.annotation.SuppressLint
import androidx.lifecycle.Transformations.map
import com.example.moviestar.databinding.DetailFragmentBinding
import com.example.moviestar.view.DetailFragment
import com.example.moviestar.view.MainAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.runBlocking
import java.util.*

class LocalRepositoryImpl(private val dao: HistoryDAO): LocalRepository {

    override fun getAllHistory(): List<Movie> = runBlocking {
        dao.all()
            .map { entity ->
                    Movie(
                        id = entity.movieId,
                        title = entity.title,
                        releaseYear = entity.releaseYear,
                        note = entity.note,
                        runtime = entity.runtime,
                        isFavourite = entity.isFavourite
                    ) }
    }

    override fun getOneMovieHistory(id: Int): Movie = runBlocking {
        Movie(
            id = dao.getOneMovieHistoryData(id).movieId,
            title = dao.getOneMovieHistoryData(id).title,
            releaseYear = dao.getOneMovieHistoryData(id).releaseYear,
            note = dao.getOneMovieHistoryData(id).note,
            runtime = dao.getOneMovieHistoryData(id).runtime,
            isFavourite = dao.getOneMovieHistoryData(id).isFavourite
        )
    }

    override fun saveEntity(movie: Movie) = runBlocking {
        dao.insert(
            HistoryEntity(
                id = 0,
                movieId = movie.id,
                title = movie.title,
                releaseYear = movie.releaseYear,
                note = movie.note,
                runtime = movie.runtime,
                timestamp = Date().time,
                isFavourite = movie.isFavourite
            )
        )
    }

    override fun updateNote(id: Int, note: String) = runBlocking {
        dao.updateNote(id, note)
    }

    override fun updateTimestamp(timestamp: Long, id: Int) =  runBlocking{
        dao.updateTimestamp(timestamp, id)
    }

    override fun updateFavourite(id: Int, isFavourite: Boolean) = runBlocking {
        dao.updateFavourite(id, isFavourite)
    }
}