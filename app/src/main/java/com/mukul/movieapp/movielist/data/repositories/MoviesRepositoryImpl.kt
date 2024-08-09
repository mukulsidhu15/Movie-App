package com.mukul.movieapp.movielist.data.repositories

import com.mukul.movieapp.movielist.data.models.toMovie
import com.mukul.movieapp.movielist.domain.models.Movie
import com.mukul.movieapp.movielist.domain.repositories.MoviesRepository
import com.mukul.movieapp.retrofit.RetrofitHelper
import com.mukul.movieapp.retrofit.response.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl : MoviesRepository {
    override suspend fun getAllMovies(): Flow<Result<List<Movie>, MoviesRepository.MovieErrors>> =
        flow {
            emit(Result.Loading())
            val response = RetrofitHelper.getMovieApiServiceInstance().getAllMovies()
            if (response.isSuccessful) {
                response.body()
                    ?.let { emit(Result.Success(it.map { movie -> movie.toMovie() })) }
            } else {
                when (response.code()) {
                    404 -> emit(Result.Error(MoviesRepository.MovieErrors.NOT_FOUND))
                    500 -> emit(Result.Error(MoviesRepository.MovieErrors.NETWORK_FAIL))
                    else -> emit(Result.Error(MoviesRepository.MovieErrors.UNKNOWN_ERROR))
                }
            }
        }
}