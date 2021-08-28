package com.talentsarena.ui.movie.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.talentsarena.core.ui.BaseFragment
import com.talentsarena.core.vm.BaseViewModel
import com.talentsarena.feature.movie.model.GetMoviesRequest
import com.talentsarena.feature.movie.usecase.MovieUseCase
import com.talentsarena.ui.movie.BuildConfig
import com.talentsarena.ui.movie.coordinator.MovieCoordinator
import com.talentsarena.ui.movie.coordinator.MovieNavigator
import com.talentsarena.ui.movie.databinding.FragmentMovieListBinding
import com.talentsarena.ui.movie.list.servicelocator.MovieListFragmentServiceLocator
import com.talentsarena.ui.movie.list.udf.MovieListUDF
import com.talentsarena.ui.movie.list.viewmodel.MovieListViewModel
import com.talentsarena.ui.movie.list.viewmodel.MovieListViewModelFactory
import com.talentsarena.ui.movie.model.MovieUiModel

/**
 * Fragment that shows [MovieUiModel] in a list appearance.
 */
class MovieListFragment :
    BaseFragment<MovieListUDF.ActionEvent, MovieListUDF.ViewState, MovieListUDF.SideEffect, FragmentMovieListBinding>() {

    companion object {
        fun newInstance(args: Bundle? = null): MovieListFragment = MovieListFragment().apply {
            arguments = args
        }
    }

    private val movieCoordinator: MovieCoordinator = MovieListFragmentServiceLocator.getCoordinator(
        navigator = MovieNavigator()
    )

    private val movieUseCase: MovieUseCase by lazy {
        MovieListFragmentServiceLocator.getMovieUseCase(apiKey = BuildConfig.API_KEY, context = requireContext())
    }

    private val getMoviesRequest = GetMoviesRequest(pageNumber = 1)

    override fun initializeViewModel(): BaseViewModel<MovieListUDF.ActionEvent, MovieListUDF.ViewState, MovieListUDF.SideEffect> {
        val viewModelFactory = MovieListViewModelFactory(movieUseCase = movieUseCase)
        return ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)
    }

    override fun initializeViewBinding(): FragmentMovieListBinding = FragmentMovieListBinding.inflate(layoutInflater)

    override fun onActivityReady(savedInstanceState: Bundle?) {
        fetchMovieList(pageNumber = 1)
    }

    override fun renderViewState(viewState: MovieListUDF.ViewState) {
        with(viewState) {
            viewBinding.movieListView.render { movieListRendering ->
                movieListRendering.toBuilder()
                    .state { movieListState ->
                        movieListState.copy(
                            movieList = movieList,
                            isSwipeToRefreshLoading = isSwipeToRefreshLoading,
                            isPaginationLoading = isPaginationLoading,
                            errorMessage = errorMessage
                        )
                    }
                    .onMovieItemSelected { movieId ->
                        emitActionEvent(MovieListUDF.ActionEvent.MovieItemClicked(movieId = movieId))
                    }
                    .onSwipeToRefresh {
                        fetchMovieList(pageNumber = 1)
                    }
                    .onNextMovieListPageSelected { pageNumber ->
                        fetchMovieList(pageNumber = pageNumber)
                    }
                    .build()
            }
        }
    }

    override fun handleSideEffect(sideEffect: MovieListUDF.SideEffect) {
        when (sideEffect) {
            is MovieListUDF.SideEffect.NavigateToMovieDetails ->
                if (activity != null && activity is AppCompatActivity) {
                    movieCoordinator.startMovieDetailsFragment(
                        activity = activity as AppCompatActivity,
                        movieId = sideEffect.movieId
                    )
                }
        }
    }

    private fun fetchMovieList(pageNumber: Int) {
        emitActionEvent(
            actionEvent = MovieListUDF.ActionEvent.FetchMovieList(
                getMoviesRequest = getMoviesRequest.copy(pageNumber = pageNumber)
            )
        )
    }
}