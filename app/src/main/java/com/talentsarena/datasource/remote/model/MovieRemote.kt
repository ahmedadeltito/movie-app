package com.talentsarena.datasource.remote.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.talentsarena.datasource.local.model.MovieLocal
import com.talentsarena.datasource.model.MovieDataSource

/**
 * Response model of movie.
 */
@Keep
@JsonClass(generateAdapter = true)
data class MovieRemote(

    @Json(name = "id")
    val id: Int,

    @Json(name = "overview")
    val overview: String? = null,

    @Json(name = "original_language")
    val originalLanguage: String? = null,

    @Json(name = "original_title")
    val originalTitle: String? = null,

    @Json(name = "video")
    val isVideo: Boolean = false,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "release_date")
    val releaseDate: String? = null,

    @Json(name = "popularity")
    val popularity: Double = 0.0,

    @Json(name = "vote_average")
    val voteAverage: Double = 0.0,

    @Json(name = "adult")
    val isAdult: Boolean = false,

    @Json(name = "vote_count")
    val voteCount: Int = 0

)

/**
 * Mapper to map [MovieRemote] to [MovieDataSource].
 */
fun MovieRemote.mapToDataSource(): MovieDataSource = MovieDataSource(
    id = id,
    overview = overview ?: "",
    originalLanguage = originalLanguage ?: "",
    originalTitle = originalTitle ?: "",
    isVideo = isVideo,
    title = title ?: "",
    posterPath = posterPath ?: "",
    releaseDate = releaseDate ?: "",
    popularity = popularity,
    voteAverage = voteAverage,
    isAdult = isAdult,
    voteCount = voteCount
)

/**
 * Mapper to map [MovieRemote] to [MovieLocal].
 */
fun MovieRemote.mapToLocal(): MovieLocal = MovieLocal(
    id = id,
    overview = overview,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    isVideo = isVideo,
    title = title,
    posterPath = posterPath,
    releaseDate = releaseDate,
    popularity = popularity,
    voteAverage = voteAverage,
    isAdult = isAdult,
    voteCount = voteCount
)
