package com.mukul.movieapp.movielist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mukul.movieapp.movielist.domain.repositories.MoviesRepository

class MovieViewModelFactory(private val moviesRepository: MoviesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            MovieViewModel(this.moviesRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}