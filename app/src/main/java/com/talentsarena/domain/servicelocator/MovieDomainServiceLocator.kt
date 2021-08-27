package com.talentsarena.domain.servicelocator

import com.talentsarena.domain.repository.MovieRepository
import com.talentsarena.domain.usecase.MovieUseCase

/**
 * Service locator to generate [MovieUseCase].
 */
object MovieDomainServiceLocator {

    fun getMovieUseCase(movieRepository: MovieRepository): MovieUseCase = MovieUseCase(movieRepository = movieRepository)
}