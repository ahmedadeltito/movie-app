package com.talentsarena.movieapp.remote.client

import com.talentsarena.movieapp.remote.model.MovieListRemote
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API Service interface that holds all movie APIs.
 */
interface MovieApiService {

    @GET("movie/popular")
    fun getMovies(@Query("page") pageNumber: Int?): Call<MovieListRemote>
}