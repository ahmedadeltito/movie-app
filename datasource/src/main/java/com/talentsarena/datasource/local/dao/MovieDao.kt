package com.talentsarena.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.talentsarena.datasource.local.model.MovieLocal

/**
 * Data access object for [MovieLocal].
 */
@Dao
interface MovieDao {
    @Query("SELECT * FROM movie ORDER BY title ASC")
    suspend fun getMovies(): List<MovieLocal>?

    @Query("SELECT * FROM movie WHERE id = :movieId")
    suspend fun getMovie(movieId: Int): MovieLocal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieLocal)
}