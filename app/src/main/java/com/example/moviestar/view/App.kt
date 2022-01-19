package com.example.moviestar.view

import android.app.Application
import androidx.room.Room
import com.example.moviestar.R
import com.example.moviestar.model.HistoryDAO
import com.example.moviestar.model.HistoryDataBase
import java.lang.Exception

class App: Application() {

    companion object{
        private var appInstance: App? = null
        private var db: HistoryDataBase? = null
        private const val DB_NAME = "History.db"

        fun getHistoryDao(): HistoryDAO {
            if (db == null) {
                synchronized(HistoryDataBase::class.java) {
                    if (db == null) {
                        appInstance?.let { app ->
                            db = Room.databaseBuilder(
                                app.applicationContext,
                                HistoryDataBase::class.java,
                                DB_NAME
                            ).build()
                        } ?: throw Exception("")
                    }
                }
            }
            return db!!.historyDAO()
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
}