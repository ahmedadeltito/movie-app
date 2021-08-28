package com.talentsarena.datasource.repository

import com.talentsarena.core.api.ApiEmptyResponse
import com.talentsarena.core.api.ApiErrorResponse
import com.talentsarena.core.api.ApiResponse
import com.talentsarena.core.api.ApiSuccessResponse
import com.talentsarena.datasource.local.dao.MovieDao
import com.talentsarena.datasource.local.model.mapToDataSource
import com.talentsarena.datasource.model.MovieDataSource
import com.talentsarena.datasource.remote.client.MovieApiService
import com.talentsarena.datasource.remote.model.MovieListRemote
import com.talentsarena.datasource.remote.model.MovieRemote
import com.talentsarena.datasource.remote.model.mapToDataSource
import com.talentsarena.datasource.remote.model.mapToLocal
import com.talentsarena.domain.repository.MovieRepository

/**
 * Implementer class for [MovieRepository]
 */
class MovieRepositoryImpl(
    private val remote: MovieApiService,
    private val local: MovieDao
) : MovieRepository {

    override fun getMovies(pageNumber: Int, success: (List<MovieDataSource>) -> Unit, apiError: (String) -> Unit) {
        val localMovies = local.movies
        if (localMovies?.isNotEmpty() == true && pageNumber == 1) {
            success.invoke(localMovies.map { it.mapToDataSource() })
        }

        val remoteMovies = remote.getMovies(pageNumber = pageNumber).execute()
        when (val apiResponse = ApiResponse.create<MovieListRemote>(remoteMovies)) {
            is ApiSuccessResponse -> {
                if (apiResponse.body.movies?.isNotEmpty() == true) {
                    val remoteMoviesToLocal = apiResponse.body.movies.mapToLocal()
                    if (localMovies != null && !localMovies.containsAll(remoteMoviesToLocal) && pageNumber == 1) {
                        local.insertMovies(apiResponse.body.movies.mapToLocal())
                    }
                    success.invoke(apiResponse.body.movies.mapToDataSource())
                }
            }
            is ApiEmptyResponse -> {
                success.invoke(emptyList())
            }
            is ApiErrorResponse -> {
                apiError.invoke(apiResponse.errorMessage)
            }
        }
    }

    override fun getMovie(movieId: Int, success: (MovieDataSource) -> Unit, apiError: (String) -> Unit) {
        val localMovie = local.getMovie(movieId = movieId)
        if (localMovie != null) {
            success.invoke(localMovie.mapToDataSource())
        }
        val remoteMovie = remote.getMovie(movieId = movieId).execute()
        when (val apiResponse = ApiResponse.create<MovieRemote>(remoteMovie)) {
            is ApiSuccessResponse -> {
                local.insertMovie(apiResponse.body.mapToLocal())
                success.invoke(apiResponse.body.mapToDataSource())
            }
            is ApiEmptyResponse -> {
                success.invoke(MovieRemote(id = -1).mapToDataSource())
            }
            is ApiErrorResponse -> {
                apiError.invoke(apiResponse.errorMessage)
            }
        }
    }
}