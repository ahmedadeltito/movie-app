package com.talentsarena.ui.movie.coordinator

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.talentsarena.core.extensions.replaceFragment
import com.talentsarena.ui.movie.list.MovieListFragment

/**
 * Navigator for movie feature.
 */
class MovieNavigator {

    fun navigateToMovieListFragment(activity: AppCompatActivity, containerViewId: Int) {
        activity.replaceFragment(
            fragment = MovieListFragment.newInstance(),
            containerViewId = containerViewId
        )
    }

    fun navigateToMovieDetailsFragment(activity: AppCompatActivity, movieId: Int) {
        Toast.makeText(activity, "Movie with id $movieId is clicked", Toast.LENGTH_SHORT).show()
    }
}