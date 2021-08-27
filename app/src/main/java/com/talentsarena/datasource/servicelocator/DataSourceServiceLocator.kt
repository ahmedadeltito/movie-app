package com.talentsarena.datasource.servicelocator

import com.talentsarena.datasource.local.dao.MovieDao
import com.talentsarena.datasource.remote.client.MovieApiService
import com.talentsarena.datasource.repository.MovieRepositoryImpl
import com.talentsarena.domain.repository.MovieRepository

/**
 * Service locator to generate [MovieRepository] object.
 */
object DataSourceServiceLocator {

    fun getMovieRepository(remote: MovieApiService, local: MovieDao): MovieRepository =
        MovieRepositoryImpl(remote = remote, local = local)
}