package com.talentsarena.ui.movie.list.udf

import com.talentsarena.core.udf.UiActionEvent
import com.talentsarena.core.udf.UiSideEffect
import com.talentsarena.core.udf.UiViewState
import com.talentsarena.feature.movie.model.GetMoviesRequest
import com.talentsarena.ui.movie.list.MovieListFragment
import com.talentsarena.ui.movie.model.MovieUiModel

/**
 * Simple Unidirectional Dataflow Layer for handling actions, view states and side effects of [MovieListFragment].
 */
class MovieListUDF {

    sealed class ActionEvent : UiActionEvent {
        data class FetchMovieList(val getMoviesRequest: GetMoviesRequest) : ActionEvent()
        object SwipeToRefresh : ActionEvent()
        data class MovieItemClicked(val movieId: Int) : ActionEvent()
    }

    data class ViewState(
        val isSwipeToRefreshLoading: Boolean = false,
        val isPaginationLoading: Boolean = false,
        val movieList: List<MovieUiModel> = emptyList(),
        val errorMessage: String? = null,
    ) : UiViewState

    sealed class SideEffect : UiSideEffect {
        data class NavigateToMovieDetails(val movieId: Int) : SideEffect()
    }
}