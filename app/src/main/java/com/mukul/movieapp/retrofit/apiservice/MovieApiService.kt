package com.mukul.movieapp.retrofit.apiservice


import com.mukul.movieapp.movielist.data.models.MovieDto
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiService {

    @GET("/")
    suspend fun getAllMovies(): Response<List<MovieDto>>
}