package com.example.moviestar.viewmodel

sealed class AppState {

    data class Success<T>(val data: T, val isRussian: Boolean) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}