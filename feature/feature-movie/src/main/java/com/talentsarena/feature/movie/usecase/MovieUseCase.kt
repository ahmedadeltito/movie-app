package com.talentsarena.feature.movie.usecase

import com.talentsarena.feature.movie.model.MovieEntity
import com.talentsarena.feature.movie.model.mapToEntity
import com.talentsarena.feature.movie.repository.MovieRepository

/**
 * Use case for handling with [MovieRepository].
 */
class MovieUseCase(private val movieRepository: MovieRepository) {

    fun getMovies(pageNumber: Int, success: (List<MovieEntity>) -> Unit, apiError: (String) -> Unit) {
        movieRepository.getMovies(
            pageNumber = pageNumber,
            success = { moviesListDataSource ->
                success.invoke(moviesListDataSource.mapToEntity())
            },
            apiError = { message ->
                apiError.invoke(message)
            }
        )
    }

    fun getMovie(movieId: Int, success: (MovieEntity) -> Unit, apiError: (String) -> Unit) {
        movieRepository.getMovie(
            movieId = movieId,
            success = { movieDataSource ->
                success.invoke(movieDataSource.mapToEntity())
            },
            apiError = { message ->
                apiError.invoke(message)
            }
        )
    }
}