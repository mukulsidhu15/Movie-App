package com.mukul.movieapp.retrofit

import com.mukul.movieapp.retrofit.apiservice.MovieApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private var movieApiService: MovieApiService? = null
    fun getMovieApiServiceInstance(): MovieApiService {
        if (movieApiService == null) {
            val retrofit = Retrofit.Builder().baseUrl("https://imdb-top-100-movies.p.rapidapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor { chain ->
                    val requestBuilder = chain.request().newBuilder()
                    requestBuilder.addHeader(
                        "x-rapidapi-key", "e572584cacmshfeb33e431485e13p167eb2jsn3c584c6f1a01"
                    )
                    requestBuilder.addHeader(
                        "x-rapidapi-host", "imdb-top-100-movies.p.rapidapi.com"
                    )
                    requestBuilder.build()
                    chain.proceed(requestBuilder.build())
                }.build()).build()
            movieApiService = retrofit.create(MovieApiService::class.java)
        }
        return movieApiService!!
    }
}