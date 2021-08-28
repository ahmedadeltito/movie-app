package com.talentsarena.feature.movie.apiservice

import com.talentsarena.datasource.remote.model.MovieListRemote
import com.talentsarena.datasource.remote.model.MovieRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * API Service interface that holds all movie APIs.
 */
interface MovieApiService {

    @GET("movie/popular")
    suspend fun getMovies(@Query("page") pageNumber: Int?): MovieListRemote

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") movieId: Int?): MovieRemote
}