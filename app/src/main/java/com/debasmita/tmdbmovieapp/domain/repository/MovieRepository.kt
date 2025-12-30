package com.debasmita.tmdbmovieapp.domain.repository

import com.debasmita.tmdbmovieapp.data.model.Movie

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovies(): Flow<PagingData<Movie>>
}

