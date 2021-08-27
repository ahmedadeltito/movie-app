package com.talentsarena.ui.movie.model

import com.talentsarena.feature.movie.model.MovieEntity

/**
 * Mapper to map [MovieEntity] to [MovieUiModel].
 */
fun MovieEntity.mapToUiModel(): MovieUiModel = MovieUiModel(
    id = id,
    overview = overview,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    title = title,
    posterPath = posterPath,
    releaseDate = releaseDate,
    popularity = popularity
)

/**
 * Mapper to map list of [MovieEntity] to list of [MovieUiModel].
 */
fun List<MovieEntity>.mapToUiModel(): List<MovieUiModel> = map { it.mapToUiModel() }