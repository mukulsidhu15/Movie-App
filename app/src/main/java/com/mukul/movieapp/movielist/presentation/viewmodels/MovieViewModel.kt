package com.mukul.movieapp.movielist.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mukul.movieapp.movielist.domain.models.Movie
import com.mukul.movieapp.movielist.domain.repositories.MoviesRepository
import com.mukul.movieapp.retrofit.response.Result
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    val movieListLoading = MutableLiveData<Boolean>()
    val movieListError = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Movie>>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getAllMovies() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            moviesRepository.getAllMovies().collectLatest { result ->
                when (result) {
                    is Result.Error -> handleMoviesListError(result.error)
                    is Result.Loading -> movieListLoading.postValue(true)
                    is Result.Success -> handleMoviesListSuccess(result.data)
                }
            }
        }
    }

    private fun handleMoviesListSuccess(data: List<Movie>) {
        movieList.postValue(data)
        movieListLoading.postValue(false)
    }

    private fun handleMoviesListError(error: MoviesRepository.MovieErrors) {
        when (error) {
            MoviesRepository.MovieErrors.NOT_FOUND -> onError("Not Found")
            MoviesRepository.MovieErrors.NETWORK_FAIL -> onError("Network Fail")
            MoviesRepository.MovieErrors.UNKNOWN_ERROR -> onError("Unknown Error")
        }
    }

    private fun onError(s: String) {
        movieListError.postValue(s)
        movieListLoading.postValue(false)
    }
}