package com.talentsarena.movieapp.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.talentsarena.movieapp.local.model.MovieLocal

/**
 * Data access object for [MovieLocal].
 */
@Dao
interface MovieDao {
    @get:Query("SELECT * FROM movie ORDER BY title ASC")
    val movies: List<MovieLocal>?

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getMovie(movieId: Int): MovieLocal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieLocal)
}