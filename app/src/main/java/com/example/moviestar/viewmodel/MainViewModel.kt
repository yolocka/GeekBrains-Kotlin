package com.example.moviestar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestar.model.Movie
import com.example.moviestar.model.Repository
import com.example.moviestar.model.RepositoryImpl
import java.lang.Exception
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repository: Repository = RepositoryImpl()

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getMovie() {
        liveDataToObserve.value = AppState.Loading

        Thread {
            Thread.sleep(3000)

            if(Random.nextBoolean()){
                val movie = repository.getMovieFromServer();
                liveDataToObserve.postValue(AppState.Success(movie))
            } else {
                liveDataToObserve.postValue(AppState.Error(Exception("Нет подключения к интернету")))
            }
        }.start()
    }
}