package com.talentsarena.feature.movie.servicelocator

import android.content.Context
import com.talentsarena.datasource.local.dao.MovieDao
import com.talentsarena.datasource.local.di.LocalServiceLocator
import com.talentsarena.feature.movie.apiservice.MovieApiService
import com.talentsarena.datasource.remote.di.RemoteServiceLocator
import com.talentsarena.feature.movie.repository.MovieRepository
import com.talentsarena.feature.movie.repository.MovieRepositoryImpl
import com.talentsarena.feature.movie.usecase.MovieUseCase

/**
 * Service locator to generate [MovieUseCase].
 */
object MovieDomainServiceLocator {

    private fun getMovieService(apiKey: String): MovieApiService =
        RemoteServiceLocator.getRetrofit(apiKey = apiKey).create(MovieApiService::class.java)

    private fun getMovieLocal(context: Context): MovieDao = LocalServiceLocator.getMovieDao(context = context)

    private fun getMovieRepository(
        apiKey: String,
        context: Context
    ): MovieRepository =
        MovieRepositoryImpl(remote = getMovieService(apiKey = apiKey), local = getMovieLocal(context = context))

    fun getMovieUseCase(
        apiKey: String,
        context: Context
    ): MovieUseCase = MovieUseCase(movieRepository = getMovieRepository(apiKey = apiKey, context = context))
}