package com.talentsarena.ui.coordinator

import androidx.appcompat.app.AppCompatActivity

/**
 * Navigation Coordinator for movie feature.
 */
class MovieCoordinator(private val navigator: MovieNavigator) {

    fun startMovieListFragment(activity: AppCompatActivity) {
        navigator.navigateToMovieListFragment(activity = activity)
    }

    fun startMovieDetailsFragment(activity: AppCompatActivity, movieId: Int) {
        navigator.navigateToMovieDetailsFragment(activity = activity, movieId = movieId)
    }
}