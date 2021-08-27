package com.talentsarena.ui.movelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talentsarena.core.thread.TaskExecutor
import com.talentsarena.domain.model.mapToUiModel
import com.talentsarena.domain.usecase.MovieUseCase
import com.talentsarena.ui.model.MovieUiModel
import com.talentsarena.ui.movelist.MovieListFragment

/**
 * View model for handling and dealing with [MovieUseCase]. This view model used in [MovieListFragment].
 */
class MovieListViewModel(
    private val movieUseCase: MovieUseCase,
    private val executor: TaskExecutor
) : ViewModel() {

    private val _movieList = MutableLiveData<List<MovieUiModel>>()
    val movieList: LiveData<List<MovieUiModel>>
        get() = _movieList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun getMovies(pageNumber: Int) {
        try {
            executor.executeOnDiskIO {
                movieUseCase.getMovies(
                    pageNumber = pageNumber,
                    success = { movieListEntity ->
                        _movieList.postValue(movieListEntity.mapToUiModel())
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

    override fun onCleared() {
        super.onCleared()
        executor.shutDown()
    }
}