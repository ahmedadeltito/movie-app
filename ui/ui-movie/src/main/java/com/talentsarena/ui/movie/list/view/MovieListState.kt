package com.talentsarena.ui.movie.list.view

import com.talentsarena.ui.movie.model.MovieUiModel

/**
 * Holds the state needed by the Movie List to render.
 *
 * @param movieList represents the list of movies.
 * @param isSwipeToRefreshLoading indicates if the list is loading or not.
 * @param isPaginationLoading indicates if the list is loading for the next page or not.
 * @param errorMessage indicates if there is some error happened during the fetch of the movie list.
 */
internal data class MovieListState internal constructor(
    internal val movieList: List<MovieUiModel> = emptyList(),
    internal val isSwipeToRefreshLoading: Boolean = true,
    internal val isPaginationLoading: Boolean = false,
    internal val errorMessage: String? = null
) {
    /**
     * Creates a new [Builder] based on this [MovieListState].
     */
    fun toBuilder(): Builder = Builder(this)

    class Builder constructor() {
        private var state = MovieListState()

        /**
         * Creates a new [Builder] based on an existing [MovieListState].
         */
        internal constructor(state: MovieListState) : this() {
            this.state = state
        }

        /**
         * Sets List of [MovieUiModel] that represents list of movies
         *
         * @param movieList Holds the movie list
         */
        fun movieList(movieList: List<MovieUiModel>): Builder = apply {
            state = state.copy(movieList = movieList)
        }

        /**
         * Sets the movie list to be is loading or not.
         *
         * @param isSwipeToRefreshLoading the boolean state of the movie list that should swipe to refresh.
         */
        fun isSwipeToRefreshLoading(isSwipeToRefreshLoading: Boolean): Builder = apply {
            state = state.copy(isSwipeToRefreshLoading = isSwipeToRefreshLoading)
        }

        /**
         * Sets the movie list to be is loading for a pagination request or not.
         *
         * @param isPaginationLoading the boolean state of the movie list that should paginated.
         */
        fun isPaginationLoading(isPaginationLoading: Boolean): Builder = apply {
            state = state.copy(isPaginationLoading = isPaginationLoading)
        }

        /**
         * Sets the error message.
         *
         * @param errorMessage Holds the error message.
         */
        fun errorMessage(errorMessage: String): Builder = apply {
            state = state.copy(errorMessage = errorMessage)
        }

        /**
         * Builds the [MovieListState]
         */
        fun build(): MovieListState = state
    }
}