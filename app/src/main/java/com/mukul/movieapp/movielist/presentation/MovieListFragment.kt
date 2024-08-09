package com.mukul.movieapp.movielist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mukul.movieapp.databinding.FragmentMovieListBinding
import com.mukul.movieapp.movielist.data.repositories.MoviesRepositoryImpl
import com.mukul.movieapp.movielist.domain.models.Movie
import com.mukul.movieapp.movielist.domain.repositories.MoviesRepository
import com.mukul.movieapp.movielist.presentation.adapters.MoviesAdapter
import com.mukul.movieapp.movielist.presentation.viewmodels.MovieViewModel
import com.mukul.movieapp.movielist.presentation.viewmodels.MovieViewModelFactory

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainRepository: MoviesRepository = MoviesRepositoryImpl()
        movieViewModel = ViewModelProvider(
            (this), MovieViewModelFactory(mainRepository)
        )[MovieViewModel::class.java]

        movieAdapter = MoviesAdapter()
        binding.moviesRecV.layoutManager = LinearLayoutManager(requireContext())
        binding.moviesRecV.adapter = movieAdapter

        movieViewModel.movieList.observe(viewLifecycleOwner) {
            setMovieList(it)
        }

        movieViewModel.movieListError.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        movieViewModel.movieListLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        movieViewModel.getAllMovies()
    }

    private fun setMovieList(movieList: List<Movie>) {
        if(movieList.isEmpty()){
            binding.infoTv.visibility = View.VISIBLE
            return
        }
        binding.infoTv.visibility = View.GONE
        movieAdapter.setMovies(movieList)
    }
}