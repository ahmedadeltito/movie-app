package com.talentsarena.movieapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talentsarena.core.thread.TaskExecutor
import com.talentsarena.movieapp.MovieListFragment

/**
 * Generates [MovieListViewModel] to be used in [MovieListFragment].
 */
@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory(
    private val apiKey: String,
    private val context: Context,
    private val executor: TaskExecutor
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MovieListViewModel(
        apiKey = apiKey, context = context, executor = executor
    ) as T
}