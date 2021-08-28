package com.talentsarena.movieapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talentsarena.core.api.ApiEmptyResponse
import com.talentsarena.core.api.ApiErrorResponse
import com.talentsarena.core.api.ApiResponse
import com.talentsarena.core.api.ApiSuccessResponse
import com.talentsarena.core.thread.TaskExecutor
import com.talentsarena.movieapp.MovieListFragment
import com.talentsarena.movieapp.local.DatabaseManager
import com.talentsarena.movieapp.local.dao.MovieDao
import com.talentsarena.movieapp.local.model.mapToUiModel
import com.talentsarena.movieapp.model.MovieUiModel
import com.talentsarena.movieapp.remote.client.MovieApiService
import com.talentsarena.movieapp.remote.client.RetrofitClient
import com.talentsarena.movieapp.remote.model.MovieListRemote
import com.talentsarena.movieapp.remote.model.mapToLocal
import com.talentsarena.movieapp.remote.model.mapToUiModel

/**
 * View model for handling and dealing with [RetrofitClient] and [DatabaseManager].
 * This view model used in [MovieListFragment].
 */
class MovieListViewModel(
    apiKey: String,
    context: Context,
    private val executor: TaskExecutor
) : ViewModel() {

    private val _movieList = MutableLiveData<List<MovieUiModel>>()
    val movieList: LiveData<List<MovieUiModel>>
        get() = _movieList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val remote: MovieApiService = RetrofitClient(apiKey = apiKey)
        .provideRetrofit()
        .create(MovieApiService::class.java)
    private val local: MovieDao = DatabaseManager.getInstance(context).movieDao()

    fun getMovies(pageNumber: Int) {
        try {
            executor.executeOnDiskIO {
                getMovies(
                    pageNumber = pageNumber,
                    success = { movieListUiModel ->
                        _movieList.postValue(movieListUiModel)
                    },
                    apiError = { message ->
                        _errorMessage.postValue(message)
                    }
                )
            }
        } catch (e: Exception) {
            _errorMessage.postValue(e.message)
        }
    }

    private fun getMovies(pageNumber: Int, success: (List<MovieUiModel>) -> Unit, apiError: (String) -> Unit) {
        val localMovies = local.movies
        if (localMovies?.isNotEmpty() == true && pageNumber == 1) {
            success.invoke(localMovies.map { it.mapToUiModel() })
        }

        val remoteMovies = remote.getMovies(pageNumber = pageNumber).execute()
        when (val apiResponse = ApiResponse.create<MovieListRemote>(remoteMovies)) {
            is ApiSuccessResponse -> {
                if (apiResponse.body.movies?.isNotEmpty() == true) {
                    val remoteMoviesToLocal = apiResponse.body.movies.mapToLocal()
                    if (localMovies != null && !localMovies.containsAll(remoteMoviesToLocal) && pageNumber == 1) {
                        local.insertMovies(apiResponse.body.movies.mapToLocal())
                    }
                    success.invoke(apiResponse.body.movies.mapToUiModel())
                }
            }
            is ApiEmptyResponse -> {
                success.invoke(emptyList())
            }
            is ApiErrorResponse -> {
                apiError.invoke(apiResponse.errorMessage)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        executor.shutDown()
    }
}