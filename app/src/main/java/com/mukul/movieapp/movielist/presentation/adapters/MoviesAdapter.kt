package com.mukul.movieapp.movielist.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mukul.movieapp.databinding.MovieItemLayoutBinding
import com.mukul.movieapp.movielist.domain.models.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var moviesList = listOf<Movie>()

    fun setMovies(movies: List<Movie>) {
        this.moviesList = movies.toMutableList()
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: MovieItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Glide.with(binding.root).load(movie.imageUrl).into(binding.movieThumbnail)
            binding.movieTitle.text = movie.name
            binding.movieDescription.text = movie.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MovieItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }
}