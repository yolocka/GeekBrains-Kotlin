package com.example.moviestar.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "HistoryEntity", indices = [(Index(value = ["movieId"], unique = true))])
data class HistoryEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val movieId: Int,
    val title: String,
    val releaseYear: String,
    val note: String,
    val runtime: Int,
    val timestamp: Long,
    val isFavourite: Boolean
    )