package com.talentsarena.movieapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talentsarena.movieapp.MovieListFragment

/**
 * Generates [MovieListViewModel] to be used in [MovieListFragment].
 */
@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MovieListViewModel(context = context) as T
}