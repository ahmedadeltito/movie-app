package com.talentsarena.ui.movie.list.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.talentsarena.core.extensions.gone
import com.talentsarena.core.extensions.visible
import com.talentsarena.core.extensions.visibleOrGone
import com.talentsarena.core.widget.EndlessRecyclerViewScrollListener
import com.talentsarena.ui.movie.databinding.MovieListViewBinding
import com.talentsarena.ui.movie.list.adapter.MovieListAdapter
import com.talentsarena.ui.movie.model.MovieUiModel

/**
 * Custom view that holds all the logic related to showing [MovieUiModel] in a list appearance.
 */
class MovieListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0,
) : FrameLayout(context, attrs, defStyleAttrs, defStyleRes) {

    private val viewBinding: MovieListViewBinding = MovieListViewBinding.inflate(LayoutInflater.from(context), this)

    private val movieListAdapter = MovieListAdapter()

    private var getMovies: ((pageNumber: Int) -> Unit)? = null
    private var onMovieClickListener: ((movieId: Int) -> Unit)? = null

    init {
        configUI()
    }

    private fun configUI() {
        with(viewBinding) {
            val endlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(
                movieListView.layoutManager as LinearLayoutManager
            ) {
                override fun onLoadMore(page: Int, totalItemsCount: Int) {
                    getMovies?.invoke(page)
                }
            }
            movieListView.apply {
                itemAnimator = DefaultItemAnimator()
                setHasFixedSize(true)
                adapter = movieListAdapter
            }

            movieListSrl.setOnRefreshListener {
                endlessRecyclerViewScrollListener.resetState()
                getMovies?.invoke(1)
            }

            movieListView.addOnScrollListener(endlessRecyclerViewScrollListener)

            movieListAdapter.onMovieClickListener = { movieId ->
                onMovieClickListener?.invoke(movieId)
            }
        }
    }

    fun addCallbacks(getMovies: (Int) -> Unit, onMovieClickListener: (Int) -> Unit) {
        this.getMovies = getMovies
        this.onMovieClickListener = onMovieClickListener
    }

    fun showLoading(isLoading: Boolean, isFirstPage: Boolean) {
        with(viewBinding) {
            if (isFirstPage) {
                movieListSrl.post {
                    movieListSrl.isRefreshing = isLoading
                }
            } else {
                loadMorePb.post {
                    loadMorePb.visibleOrGone(show = isLoading)
                }
            }
        }
    }

    fun showMovies(movies: List<MovieUiModel>) {
        with(viewBinding) {
            loadMorePb.gone()
            emptyListTv.gone()
            movieListView.visible()

            movieListAdapter.submitList(movies)
        }
    }

    fun showErrorMessage(message: String?) {
        with(viewBinding) {
            loadMorePb.gone()
            emptyListTv.visible()
            movieListView.gone()

            emptyListTv.text = message
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        getMovies = null
        onMovieClickListener = null
    }
}