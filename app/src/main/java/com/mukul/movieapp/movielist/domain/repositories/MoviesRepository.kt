package com.mukul.movieapp.movielist.domain.repositories

import com.mukul.movieapp.movielist.domain.models.Movie
import com.mukul.movieapp.retrofit.response.Error
import com.mukul.movieapp.retrofit.response.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getAllMovies(): Flow<Result<List<Movie>, MovieErrors>>

    enum class MovieErrors : Error {
        NOT_FOUND, NETWORK_FAIL, UNKNOWN_ERROR
    }
}