package com.example.moviestar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestar.model.LocalRepository
import com.example.moviestar.model.LocalRepositoryImpl
import com.example.moviestar.model.Movie
import com.example.moviestar.model.RepositoryImpl
import com.example.moviestar.view.App
import com.example.moviestar.view.MainAdapter
import java.lang.Exception
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val localRepo: LocalRepository = LocalRepositoryImpl(App.getHistoryDao())

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getMovieFromLocalStorageRus() = getDataFromLocalSource(true)
    fun getMovieFromLocalStorageWorld() = getDataFromLocalSource(false)
    fun getMovieFromRemoteSource() = getDataFromLocalSource(true)

    fun getAllHistory(): List<Movie> {
        return localRepo.getAllHistory()
    }

    fun getDataFromLocalSource(isRussian: Boolean = true) {
        liveDataToObserve.value = AppState.Loading

        Thread {
            Thread.sleep(1000)

            if(Random.nextBoolean() || Random.nextBoolean()){
                val movie = if (isRussian) {
                    RepositoryImpl.getMovieFromLocalStorageRus()
                } else {
                    RepositoryImpl.getMovieFromLocalStorageWorld()
                }
                liveDataToObserve.postValue(AppState.Success(movie, isRussian))
            } else {
                liveDataToObserve.postValue(AppState.Error(Exception("Нет подключения к интернету")))
            }
        }.start()
    }
}