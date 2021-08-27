package com.talentsarena.ui.movie.list.servicelocator

import android.content.Context
import com.talentsarena.feature.movie.servicelocator.MovieDomainServiceLocator
import com.talentsarena.feature.movie.usecase.MovieUseCase
import com.talentsarena.ui.movie.coordinator.MovieCoordinator
import com.talentsarena.ui.movie.coordinator.MovieNavigator

/**
 * Service locator to generate [MovieCoordinator], and [MovieUseCase].
 */
object MovieListFragmentServiceLocator {

    fun getCoordinator(navigator: MovieNavigator): MovieCoordinator = MovieCoordinator(navigator)

    fun getMovieUseCase(apiKey: String, context: Context): MovieUseCase {
        return MovieDomainServiceLocator.getMovieUseCase(apiKey = apiKey, context = context)
    }
}