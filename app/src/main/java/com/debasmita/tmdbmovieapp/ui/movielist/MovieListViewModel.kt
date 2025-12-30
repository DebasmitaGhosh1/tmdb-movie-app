package com.debasmita.tmdbmovieapp.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.debasmita.tmdbmovieapp.data.model.Movie
import com.debasmita.tmdbmovieapp.network.NetworkModule
import com.debasmita.tmdbmovieapp.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

//class MovieListViewModel : ViewModel() {
//
//    private val repo =
//        MovieRepositoryImpl(NetworkModule.api())
//
//    val movies = repo.fetchMovies()
//        .cachedIn(viewModelScope)
//}

class MovieListViewModel : ViewModel() {

    private val repo =
        MovieRepositoryImpl(NetworkModule.api())
    private val searchQuery = MutableStateFlow("")

    val movies: Flow<PagingData<Movie>> =
        searchQuery.flatMapLatest { query ->
            if (query.isBlank()) {
                repo.fetchMovies()
            } else {
                repo.searchMovies(query)
            }
        }.cachedIn(viewModelScope)

    fun search(query: String) {
        searchQuery.value = query
    }



}
