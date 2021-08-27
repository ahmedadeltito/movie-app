package com.talentsarena.ui.coordinator

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.talentsarena.core.extensions.replaceFragment
import com.talentsarena.movieapp.R
import com.talentsarena.ui.movelist.MovieListFragment

/**
 * Navigator for movie feature.
 */
class MovieNavigator {

    fun navigateToMovieListFragment(activity: AppCompatActivity) {
        activity.replaceFragment(
            fragment = MovieListFragment.newInstance(),
            containerViewId = R.id.activity_movie_container
        )
    }

    fun navigateToMovieDetailsFragment(activity: AppCompatActivity, movieId: Int) {
        Toast.makeText(activity, "Movie with id $movieId is clicked", Toast.LENGTH_SHORT).show()
    }
}