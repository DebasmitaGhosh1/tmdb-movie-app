package com.debasmita.tmdbmovieapp.ui.moviedetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.debasmita.tmdbmovieapp.R
import com.debasmita.tmdbmovieapp.databinding.FragmentMovieDetailBinding
import kotlin.getValue
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.debasmita.tmdbmovieapp.data.repository.MovieDetailsRepository
import com.debasmita.tmdbmovieapp.network.NetworkModule
import com.debasmita.tmdbmovieapp.ui.moviedetail.MovieDetailViewModel
import com.debasmita.tmdbmovieapp.ui.moviedetail.MovieDetailViewModelFactory


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!


    
//    private val viewModel: MovieDetailViewModel by viewModels {
//        MovieDetailViewModel(
//            MovieDetailsRepository(NetworkModule.api())
//        )
//    }

    private val viewModel: MovieDetailViewModel by viewModels {
        MovieDetailViewModelFactory(
            MovieDetailsRepository(NetworkModule.api())
        )
    }

    private val args: MovieDetailFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieDetailBinding.bind(view)

        val movie = args.movie


        binding.detailTitle.text = movie.title

        binding.txtRating.text="${movie.rating}"
        binding.detailOverview.text = movie.overview

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(binding.detailPoster)

        viewModel.loadMovieDetails(movie.id)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.runtime.collect {

                //Log.d("MovieDetailFragment", "runtime: $it")

                binding.txtYear.text =
                    "${movie.releaseDate.take(4)} | $it"
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.director.collect {
                binding.director.text = it
            }
        }


        // Load runtime + director
        /*
        viewModel.loadDetails(movie.id)

        lifecycleScope.launchWhenStarted {
            viewModel.runtime.collectLatest {
                binding.detailRuntime.text = "Duration: $it"
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.director.collectLatest {
                binding.detailDirector.text = "Director: $it"
            }
        }

         */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}