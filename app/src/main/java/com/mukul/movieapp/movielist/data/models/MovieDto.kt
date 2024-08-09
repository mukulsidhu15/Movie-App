package com.mukul.movieapp.movielist.data.models

import com.google.gson.annotations.SerializedName
import com.mukul.movieapp.movielist.domain.models.Movie

data class MovieDto(
    @SerializedName("big_image")
    val bigImage: String,
    val description: String,
    val genre: List<String>,
    val id: String,
    val image: String,
    @SerializedName("imdb_link")
    val imdbLink: String,
    @SerializedName("imdbid")
    val imdbId: String,
    val rank: Int,
    val rating: String,
    val thumbnail: String,
    val title: String,
    val year: Int
)

fun MovieDto.toMovie() = Movie(title, image, description)