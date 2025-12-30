package com.debasmita.tmdbmovieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.debasmita.tmdbmovieapp.data.model.Movie
import com.debasmita.tmdbmovieapp.data.remote.MovieApiService
import com.debasmita.tmdbmovieapp.data.remote.MoviePagingSource
import com.debasmita.tmdbmovieapp.data.remote.SearchMoviePagingSource
import com.debasmita.tmdbmovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val api: MovieApiService
) : MovieRepository {

    override fun fetchMovies(): Flow<PagingData<Movie>> {
        return Pager(
            PagingConfig(pageSize = 20)
        ) {
            MoviePagingSource(api)
        }.flow
    }

    fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            PagingConfig(pageSize = 20)
        ) {
            SearchMoviePagingSource(api, query)
        }.flow
    }

}

