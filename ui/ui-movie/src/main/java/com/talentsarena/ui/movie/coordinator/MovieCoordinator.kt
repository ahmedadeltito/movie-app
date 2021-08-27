package com.talentsarena.ui.movie.coordinator

import androidx.appcompat.app.AppCompatActivity

/**
 * Navigation Coordinator for movie feature.
 */
class MovieCoordinator(private val navigator: MovieNavigator) {

    fun startMovieListFragment(activity: AppCompatActivity, containerViewId: Int) {
        navigator.navigateToMovieListFragment(activity = activity, containerViewId = containerViewId)
    }

    fun startMovieDetailsFragment(activity: AppCompatActivity, movieId: Int) {
        navigator.navigateToMovieDetailsFragment(activity = activity, movieId = movieId)
    }
}