package com.talentsarena.feature.movie.model

import com.talentsarena.datasource.model.MovieDataSource

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