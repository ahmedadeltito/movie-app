package com.talentsarena.ui.movie.model

/**
 * Ui model of movie.
 */
data class MovieUiModel(
    val id: Int,
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val popularity: Double
)