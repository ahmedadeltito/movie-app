package com.talentsarena.feature.movie.repository

import com.talentsarena.datasource.local.dao.MovieDao
import com.talentsarena.datasource.local.model.mapToDataSource
import com.talentsarena.datasource.model.MovieDataSource
import com.talentsarena.datasource.remote.model.mapToDataSource
import com.talentsarena.datasource.remote.model.mapToLocal
import com.talentsarena.feature.movie.apiservice.MovieApiService
import com.talentsarena.feature.movie.model.GetMovieRequest
import com.talentsarena.feature.movie.model.GetMoviesRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementer class for [MovieRepository]
 */
internal class MovieRepositoryImpl(
    private val remote: MovieApiService,
    private val local: MovieDao
) : MovieRepository {

    override suspend fun getMovies(getMoviesRequest: GetMoviesRequest): Flow<List<MovieDataSource>> = flow {
        val localMovies = local.getMovies()
        if (localMovies?.isNotEmpty() == true && getMoviesRequest.pageNumber == 1) {
            emit(value = localMovies.mapToDataSource())
        }
        val remoteMovies = remote.getMovies(pageNumber = getMoviesRequest.pageNumber).movies
        if (remoteMovies?.isNotEmpty() == true) {
            val remoteMoviesToLocal = remoteMovies.mapToLocal()
            if (localMovies != null && !localMovies.containsAll(remoteMoviesToLocal)) {
                if (getMoviesRequest.pageNumber == 1) {
                    local.insertMovies(movies = remoteMovies.mapToLocal())
                }
                emit(value = remoteMovies.mapToDataSource())
            }
        }
    }

    override suspend fun getMovie(getMovieRequest: GetMovieRequest): Flow<MovieDataSource> = flow {
        val localMovie = local.getMovie(movieId = getMovieRequest.movieId)
        val localDataSource = localMovie?.mapToDataSource()
        if (localDataSource != null) {
            emit(value = localDataSource)
        }

        val remoteMovie = remote.getMovie(movieId = getMovieRequest.movieId)
        val remoteDataSource = remoteMovie.mapToDataSource()

        if (localDataSource != remoteDataSource) {
            local.insertMovie(remoteMovie.mapToLocal())
            emit(value = remoteDataSource)
        }
    }
}