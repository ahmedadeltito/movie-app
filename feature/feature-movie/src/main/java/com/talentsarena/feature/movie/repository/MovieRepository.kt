package com.talentsarena.feature.movie.repository

import com.talentsarena.datasource.model.MovieDataSource
import com.talentsarena.feature.movie.model.GetMovieRequest
import com.talentsarena.feature.movie.model.GetMoviesRequest
import kotlinx.coroutines.flow.Flow

/**
 * Repository class that holds all the [MovieDataSource] CRUD operations and.
 */
interface MovieRepository {
    suspend fun getMovies(getMoviesRequest: GetMoviesRequest): Flow<List<MovieDataSource>>
    suspend fun getMovie(getMovieRequest: GetMovieRequest): Flow<MovieDataSource>
}
