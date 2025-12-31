package com.debasmita.tmdbmovieapp.data.repository

import com.debasmita.tmdbmovieapp.BuildConfig
import com.debasmita.tmdbmovieapp.data.remote.MovieApiService

class MovieDetailsRepository(private val api: MovieApiService) {

    suspend fun getMovieDetails(movieId: Int) =
        api.getMovieDetails(movieId, BuildConfig.TMDB_API_KEY)

    suspend fun getMovieCredits(movieId: Int) =
        api.getMovieCredits(movieId, BuildConfig.TMDB_API_KEY)
}