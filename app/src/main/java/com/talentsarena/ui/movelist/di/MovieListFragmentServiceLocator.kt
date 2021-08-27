package com.talentsarena.ui.movelist.di

import android.content.Context
import com.talentsarena.datasource.local.di.LocalServiceLocator
import com.talentsarena.datasource.remote.client.MovieApiService
import com.talentsarena.datasource.remote.di.RemoteServiceLocator
import com.talentsarena.datasource.servicelocator.DataSourceServiceLocator
import com.talentsarena.domain.servicelocator.MovieDomainServiceLocator
import com.talentsarena.domain.usecase.MovieUseCase
import com.talentsarena.ui.coordinator.MovieCoordinator
import com.talentsarena.ui.coordinator.MovieNavigator

/**
 * Service locator to generate [MovieCoordinator], and [MovieUseCase].
 */
object MovieListFragmentServiceLocator {

    fun getCoordinator(navigator: MovieNavigator): MovieCoordinator = MovieCoordinator(navigator)

    fun getMovieUseCase(apiKey: String, context: Context): MovieUseCase {
        val remote = RemoteServiceLocator.getRetrofit(apiKey = apiKey).create(MovieApiService::class.java)
        val local = LocalServiceLocator.getMovieDao(context = context)
        val movieRepository = DataSourceServiceLocator.getMovieRepository(remote = remote, local = local)
        return MovieDomainServiceLocator.getMovieUseCase(movieRepository = movieRepository)
    }
}