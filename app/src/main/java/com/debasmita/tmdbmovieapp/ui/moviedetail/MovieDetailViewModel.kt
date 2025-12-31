package com.debasmita.tmdbmovieapp.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debasmita.tmdbmovieapp.data.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val repository: MovieDetailsRepository
) : ViewModel() {

    private val _runtime = MutableStateFlow("N/A")
    val runtime = _runtime.asStateFlow()

    private val _director = MutableStateFlow("N/A")
    val director = _director.asStateFlow()

    fun loadMovieDetails(movieId: Int) {
        //--
        viewModelScope.launch {
            val details = repository.getMovieDetails(movieId)
            _runtime.value = formatRuntime(details.runtime)

            val credits = repository.getMovieCredits(movieId)
            _director.value = credits.crew
                .firstOrNull { it.job == "Director" }
                ?.name ?: "N/A"
        }
    }

    private fun formatRuntime(runtime: Int?): String {
        if (runtime == null || runtime == 0) return "N/A"
        val h = runtime / 60
        val m = runtime % 60
        return "${h}h ${m}m"
    }
}
