package com.talentsarena.movieapp.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.talentsarena.movieapp.model.MovieUiModel

/**
 * Local model for movies.
 */
@Entity(tableName = "movie")
data class MovieLocal(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String? = null,

    @ColumnInfo(name = "original_title")
    val originalTitle: String? = null,

    @ColumnInfo(name = "video")
    val isVideo: Boolean = false,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,

    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,

    @ColumnInfo(name = "popularity")
    val popularity: Double = 0.0,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double = 0.0,

    @ColumnInfo(name = "adult")
    val isAdult: Boolean = false,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int = 0

)

/**
 * Mapper to map [MovieLocal] to [MovieUiModel].
 */
fun MovieLocal.mapToUiModel(): MovieUiModel = MovieUiModel(
    id = id,
    overview = overview ?: "",
    originalLanguage = originalLanguage ?: "",
    originalTitle = originalTitle ?: "",
    title = title ?: "",
    posterPath = posterPath ?: "",
    releaseDate = releaseDate ?: "",
    popularity = popularity,
)