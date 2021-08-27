package com.talentsarena.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.talentsarena.datasource.local.dao.MovieDao
import com.talentsarena.datasource.local.model.MovieLocal

/**
 * Base class for all Room databases in our application. For know, we only have one room entity which is [MovieLocal].
 */
@Database(
    entities = [
        MovieLocal::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: DatabaseManager? = null

        fun getInstance(context: Context): DatabaseManager = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                DatabaseManager::class.java,
                "movie-app"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}