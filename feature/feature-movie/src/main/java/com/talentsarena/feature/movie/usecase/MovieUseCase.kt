package com.talentsarena.feature.movie.usecase

import com.talentsarena.feature.movie.model.GetMovieRequest
import com.talentsarena.feature.movie.model.GetMoviesRequest
import com.talentsarena.feature.movie.model.MovieEntity
import com.talentsarena.feature.movie.model.mapToEntity
import com.talentsarena.feature.movie.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Use case for handling with [MovieRepository].
 */
class MovieUseCase(private val movieRepository: MovieRepository) {

    suspend fun getMovies(getMoviesRequest: GetMoviesRequest): Flow<List<MovieEntity>> =
        movieRepository.getMovies(getMoviesRequest = getMoviesRequest).map { movieListDataSource ->
            movieListDataSource.mapToEntity()
        }

    suspend fun getMovie(getMovieRequest: GetMovieRequest): Flow<MovieEntity> =
        movieRepository.getMovie(getMovieRequest = getMovieRequest).map { movieDataSource ->
            movieDataSource.mapToEntity()
        }
}