package com.debasmita.tmdbmovieapp.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.debasmita.tmdbmovieapp.data.repository.MovieDetailsRepository

class MovieDetailViewModelFactory(
    private val repository: MovieDetailsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(repository) as T
    }
}