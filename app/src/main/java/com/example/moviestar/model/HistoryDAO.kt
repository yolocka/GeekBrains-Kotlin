package com.example.moviestar.model

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface HistoryDAO {

    @Query("SELECT * FROM HistoryEntity ORDER BY timestamp DESC")
    suspend fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE movieId=:id")
    suspend fun getOneMovieHistoryData(id: Int): HistoryEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: HistoryEntity)

    @Query ("UPDATE HistoryEntity SET note=:note WHERE movieId=:id")
    suspend fun updateNote(id: Int, note: String)

    @Query ("UPDATE HistoryEntity SET timestamp=:timestamp WHERE movieId=:id")
    suspend fun updateTimestamp(timestamp: Long, id: Int)

    @Delete
    suspend fun delete(entity: HistoryEntity)

}