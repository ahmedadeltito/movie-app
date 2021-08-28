package com.talentsarena.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.talentsarena.core.extensions.getFragmentTag
import com.talentsarena.core.extensions.replaceFragment
import com.talentsarena.movieapp.databinding.ActivityHomeScreenBinding

/**
 * Home screen that shows after [MainActivity] shows.
 */
class HomeScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        showToolbarUpArrow()

        if (savedInstanceState == null) {
            replaceFragment(
                fragment = MovieListFragment.newInstance(),
                containerViewId = R.id.activity_movie_container
            )
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