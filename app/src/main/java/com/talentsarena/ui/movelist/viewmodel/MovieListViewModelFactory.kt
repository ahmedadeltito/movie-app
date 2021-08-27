package com.talentsarena.ui.movelist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talentsarena.core.thread.TaskExecutor
import com.talentsarena.domain.usecase.MovieUseCase
import com.talentsarena.ui.movelist.MovieListFragment

/**
 * Generates [MovieListViewModel] to be used in [MovieListFragment].
 */
@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory(
    private val movieUseCase: MovieUseCase,
    private val executor: TaskExecutor
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MovieListViewModel(
        movieUseCase = movieUseCase, executor = executor
    ) as T
}