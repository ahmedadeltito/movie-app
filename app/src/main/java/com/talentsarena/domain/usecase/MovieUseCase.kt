package com.talentsarena.domain.usecase

import com.talentsarena.datasource.model.mapToEntity
import com.talentsarena.domain.model.MovieEntity
import com.talentsarena.domain.repository.MovieRepository

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