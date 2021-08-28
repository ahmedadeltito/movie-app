package com.talentsarena.ui.movie.list.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talentsarena.core.extensions.gone
import com.talentsarena.core.extensions.visible
import com.talentsarena.core.extensions.visibleOrGone
import com.talentsarena.core.ui.Rendering
import com.talentsarena.core.widget.EndlessRecyclerViewScrollListener
import com.talentsarena.ui.movie.databinding.MovieListViewBinding
import com.talentsarena.ui.movie.list.adapter.MovieListAdapter
import com.talentsarena.ui.movie.model.MovieUiModel

/**
 * Custom view that holds all the logic related to showing [MovieUiModel] in a list appearance.
 */
internal class MovieListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0,
) : FrameLayout(context, attrs, defStyleAttrs, defStyleRes), Rendering<MovieListRendering> {

    private val viewBinding: MovieListViewBinding = MovieListViewBinding.inflate(LayoutInflater.from(context), this)

    private val adapter = MovieListAdapter()

    private var rendering: MovieListRendering = MovieListRendering()

    init {
        with(viewBinding) {
            movieListSrl.setOnRefreshListener {
                rendering.onSwipeToRefresh.invoke()
            }
            with(movieListView) {
                itemAnimator = DefaultItemAnimator()
                setHasFixedSize(true)
                adapter = this@MovieListView.adapter
                addOnScrollListener(
                    object : EndlessRecyclerViewScrollListener(movieListView.layoutManager as LinearLayoutManager) {
                        override fun onLoadMore(page: Int, totalItemsCount: Int) {
                            rendering.onNextMovieListPageSelected(page)
                            movieListViewScrollTop.visible()
                        }
                    }
                )
            }
            movieListViewScrollTop.setOnClickListener {
                movieListView.smoothScrollToPosition(0)
            }
            with(adapter) {
                stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                onMovieClickListener = { movieId ->
                    rendering.onMovieItemSelected(movieId)
                }
            }
        }
    }

    override fun render(renderingUpdate: (MovieListRendering) -> MovieListRendering) {
        rendering = renderingUpdate(rendering)

        with(viewBinding) {
            movieListSrl.post {
                movieListSrl.isRefreshing = rendering.state.isSwipeToRefreshLoading
            }
            loadMorePb.post {
                loadMorePb.visibleOrGone(show = rendering.state.isPaginationLoading)
            }

            val movieList = rendering.state.movieList
            if (movieList.isNotEmpty()) {
                emptyListTv.gone()
                movieListView.visible()
                adapter.submitList(movieList)
            }

            val errorMessage = rendering.state.errorMessage
            if (errorMessage?.isNotEmpty() == true) {
                emptyListTv.visible()
                movieListView.gone()
                emptyListTv.text = errorMessage
            }
        }
    }
}