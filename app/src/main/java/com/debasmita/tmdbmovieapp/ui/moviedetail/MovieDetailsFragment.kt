package com.debasmita.tmdbmovieapp.ui.moviedetail

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
import com.debasmita.tmdbmovieapp.network.NetworkModule
import kotlinx.coroutines.flow.collectLatest
import kotlin.getValue

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val args: MovieDetailFragmentArgs by navArgs()
    
//    private val viewModel: MovieDetailViewModel by viewModels {
//        MovieDetailViewModel(
//            MovieDetailsRepository(NetworkModule.api())
//        )
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieDetailBinding.bind(view)

        val movie = args.movie


        binding.detailTitle.text = movie.title
        binding.txtYear.text =
            "${movie.releaseDate.take(4)}"
        binding.txtRating.text="${movie.rating}"
        binding.detailOverview.text = movie.overview

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(binding.detailPoster)


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