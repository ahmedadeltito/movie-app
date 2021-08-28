package com.talentsarena.ui.movie.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talentsarena.feature.movie.usecase.MovieUseCase
import com.talentsarena.ui.movie.list.MovieListFragment

/**
 * Generates [MovieListViewModel] to be used in [MovieListFragment].
 */
@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory(private val movieUseCase: MovieUseCase) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MovieListViewModel(movieUseCase = movieUseCase) as T
}