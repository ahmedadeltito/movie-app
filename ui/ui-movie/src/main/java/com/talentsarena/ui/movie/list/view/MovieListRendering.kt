package com.talentsarena.ui.movie.list.view

/**
 * Everything that the Movie List needs to render and dispatch events.
 */
internal class MovieListRendering internal constructor(builder: Builder) {

    internal val onMovieItemSelected: OnMovieItemSelected = builder.onMovieItemSelected
    internal val onSwipeToRefresh: OnSwipeToRefresh = builder.onSwipeToRefresh
    internal val onNextMovieListPageSelected: OnNextMovieListPageSelected = builder.onNextMovieListPageSelected
    internal val state: MovieListState = builder.state

    constructor() : this(Builder())

    /**
     * Creates a new [Builder] based on this [MovieListRendering].
     */
    fun toBuilder(): Builder = Builder(this)

    /**
     * Creates a new instances of the [Builder].
     */
    class Builder() {
        internal var onMovieItemSelected: OnMovieItemSelected = { _ -> }
        internal var onSwipeToRefresh: OnSwipeToRefresh = { }
        internal var onNextMovieListPageSelected: OnNextMovieListPageSelected = { _ -> }
        internal var state: MovieListState = MovieListState()

        /**
         *  Configures this [Builder] based on the given [rendering].
         */
        internal constructor(rendering: MovieListRendering = MovieListRendering()) : this() {
            this.onMovieItemSelected = rendering.onMovieItemSelected
            this.onSwipeToRefresh = rendering.onSwipeToRefresh
            this.onNextMovieListPageSelected = rendering.onNextMovieListPageSelected
            this.state = rendering.state
        }

        /**
         * Sets the listener to be called when the ID of the movie item is selected.
         *
         * @param onMovieItemSelected the listener to be called
         *
         * @return this [Builder]
         */
        fun onMovieItemSelected(onMovieItemSelected: OnMovieItemSelected): Builder = apply {
            this.onMovieItemSelected = onMovieItemSelected
        }

        /**
         * Sets the listener to be called when the swipe to refresh is triggered.
         *
         * @param onSwipeToRefresh the listener to be called
         *
         * @return this [Builder]
         */
        fun onSwipeToRefresh(onSwipeToRefresh: OnSwipeToRefresh): Builder = apply {
            this.onSwipeToRefresh = onSwipeToRefresh
        }

        /**
         * Sets the listener to be called when the next movie list page is triggered.
         *
         * @param onNextMovieListPageSelected the listener to be called
         *
         * @return this [Builder]
         */
        fun onNextMovieListPageSelected(onNextMovieListPageSelected: OnNextMovieListPageSelected): Builder = apply {
            this.onNextMovieListPageSelected = onNextMovieListPageSelected
        }

        /**
         * Applies the [stateUpdate] on the current [MovieListState] to movie a new state to render.
         */
        fun state(stateUpdate: (MovieListState) -> MovieListState): Builder = apply {
            this.state = stateUpdate(state)
        }

        /**
         * Builds the [MovieListRendering].
         */
        fun build(): MovieListRendering = MovieListRendering(this)
    }
}

/**
 * A listener called when the ID of the movie item is selected.
 */
internal typealias OnMovieItemSelected = (Int) -> Unit

/**
 * A listener called when the Swipe To Refresh is triggered.
 */
internal typealias OnSwipeToRefresh = () -> Unit

/**
 * A listener called when the next movie list page is triggered.
 */
internal typealias OnNextMovieListPageSelected = (Int) -> Unit