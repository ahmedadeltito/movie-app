package com.talentsarena.ui.movie.list.viewmodel

import androidx.lifecycle.viewModelScope
import com.talentsarena.core.vm.BaseViewModel
import com.talentsarena.feature.movie.model.GetMoviesRequest
import com.talentsarena.feature.movie.usecase.MovieUseCase
import com.talentsarena.ui.movie.list.MovieListFragment
import com.talentsarena.ui.movie.list.udf.MovieListUDF
import com.talentsarena.ui.movie.model.mapToUiModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * View model for handling and dealing with [MovieUseCase]. This view model used in [MovieListFragment].
 */
class MovieListViewModel(
    private val movieUseCase: MovieUseCase
) : BaseViewModel<MovieListUDF.ActionEvent, MovieListUDF.ViewState, MovieListUDF.SideEffect>() {

    override fun createInitialState(): MovieListUDF.ViewState = MovieListUDF.ViewState()

    override fun handleActionEvents(actionEvent: MovieListUDF.ActionEvent) {
        when (actionEvent) {
            is MovieListUDF.ActionEvent.FetchMovieList ->
                fetchMovieList(getMoviesRequest = actionEvent.getMoviesRequest)
            is MovieListUDF.ActionEvent.SwipeToRefresh ->
                fetchMovieList(getMoviesRequest = GetMoviesRequest(pageNumber = 1))
            is MovieListUDF.ActionEvent.MovieItemClicked ->
                emitSideEffect { MovieListUDF.SideEffect.NavigateToMovieDetails(movieId = actionEvent.movieId) }
        }
    }

    private fun fetchMovieList(getMoviesRequest: GetMoviesRequest) {
        viewModelScope.launch {
            movieUseCase.getMovies(getMoviesRequest = getMoviesRequest)
                .onStart {
                    emitViewState {
                        copy(
                            isSwipeToRefreshLoading = getMoviesRequest.pageNumber == 1,
                            isPaginationLoading = getMoviesRequest.pageNumber > 1,
                            errorMessage = null
                        )
                    }
                }
                .onCompletion {
                    emitViewState {
                        copy(isSwipeToRefreshLoading = false, isPaginationLoading = false, errorMessage = null)
                    }
                }
                .catch { throwable ->
                    emitViewState {
                        copy(errorMessage = handleThrowable(throwable = throwable))
                    }
                }
                .collect { movieListEntity ->
                    emitViewState {
                        val newMovieList = movieListEntity.mapToUiModel()
                        copy(movieList = this.movieList + newMovieList, errorMessage = null)
                    }
                }
        }
    }
}