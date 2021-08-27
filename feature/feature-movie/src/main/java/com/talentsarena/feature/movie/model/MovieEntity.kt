package com.talentsarena.feature.movie.model

/**
 * Entity model for movie.
 */
data class MovieEntity(
    val id: Int,
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val popularity: Double,
    val isAdult: Boolean
)