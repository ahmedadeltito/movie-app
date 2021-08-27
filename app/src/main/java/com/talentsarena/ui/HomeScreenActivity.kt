package com.talentsarena.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.talentsarena.core.extensions.getFragmentTag
import com.talentsarena.movieapp.MainActivity
import com.talentsarena.movieapp.databinding.ActivityHomeScreenBinding
import com.talentsarena.ui.coordinator.MovieCoordinator
import com.talentsarena.ui.coordinator.MovieNavigator
import com.talentsarena.ui.movelist.MovieListFragment
import com.talentsarena.ui.movelist.di.MovieListFragmentServiceLocator

/**
 * Home screen that shows after [MainActivity] shows.
 */
class HomeScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityHomeScreenBinding

    private val movieCoordinator: MovieCoordinator = MovieListFragmentServiceLocator.getCoordinator(
        navigator = MovieNavigator()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        showToolbarUpArrow()

        if (savedInstanceState == null) {
            movieCoordinator.startMovieListFragment(activity = this)
        }
    }

    override fun onBackPressed() {
        if (getFragmentTag()?.equals(MovieListFragment::class.java.simpleName) == true) {
            this@HomeScreenActivity.finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun showToolbarUpArrow() {
        setSupportActionBar(viewBinding.activityMovieToolbar)
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }
}