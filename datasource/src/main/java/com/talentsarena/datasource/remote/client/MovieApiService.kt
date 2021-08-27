package com.talentsarena.datasource.remote.client

import com.talentsarena.datasource.remote.model.MovieListRemote
import com.talentsarena.datasource.remote.model.MovieRemote
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * API Service interface that holds all movie APIs.
 */
interface MovieApiService {

    @GET("movie/popular")
    fun getMovies(@Query("page") pageNumber: Int?): Call<MovieListRemote>

    @GET("movie/{id}")
    fun getMovie(@Path("id") movieId: Int?): Call<MovieRemote>
}