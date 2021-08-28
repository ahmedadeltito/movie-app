package com.talentsarena.movieapp.remote.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.talentsarena.movieapp.local.model.MovieLocal
import com.talentsarena.movieapp.model.MovieUiModel

/**
 * Response model of movie list.
 */
@Keep
@JsonClass(generateAdapter = true)
data class MovieListRemote(

    @Json(name = "page")
    val page: Int = 0,

    @Json(name = "total_pages")
    val totalPages: Int = 0,

    @Json(name = "results")
    val movies: List<MovieRemote>? = null,

    @Json(name = "total_results")
    val totalResults: Int = 0

)

/**
 * Mapper to map List of [MovieRemote] to List of [MovieUiModel].
 */
fun List<MovieRemote>.mapToUiModel(): List<MovieUiModel> = map { it.mapToUiModel() }

/**
 * Mapper to map List of [MovieRemote] to List of [MovieLocal].
 */
fun List<MovieRemote>.mapToLocal(): List<MovieLocal> = map { it.mapToLocal() }