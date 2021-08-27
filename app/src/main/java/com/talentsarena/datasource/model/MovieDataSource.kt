package com.talentsarena.datasource.model

import com.talentsarena.domain.model.MovieEntity

/**
 * Data source model for movie.
 */
data class MovieDataSource(
    val id: Int,
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val isVideo: Boolean,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val isAdult: Boolean,
    val voteCount: Int
)

/**
 * Mapper to map [MovieDataSource] to [MovieEntity].
 */
fun MovieDataSource.mapToEntity(): MovieEntity = MovieEntity(
    id = id,
    overview = overview,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    title = title,
    posterPath = posterPath,
    releaseDate = releaseDate,
    popularity = popularity,
    isAdult = isAdult
)

/**
 * Mapper to map list of [MovieDataSource] to list [MovieEntity].
 */
fun List<MovieDataSource>.mapToEntity(): List<MovieEntity> = map { it.mapToEntity() }