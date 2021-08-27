package com.talentsarena.datasource.remote.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.talentsarena.datasource.local.model.MovieLocal
import com.talentsarena.datasource.model.MovieDataSource

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
 * Mapper to map List of [MovieRemote] to List of [MovieDataSource].
 */
fun List<MovieRemote>.mapToDataSource(): List<MovieDataSource> = map { it.mapToDataSource() }

/**
 * Mapper to map List of [MovieRemote] to List of [MovieLocal].
 */
fun List<MovieRemote>.mapToLocal(): List<MovieLocal> = map { it.mapToLocal() }