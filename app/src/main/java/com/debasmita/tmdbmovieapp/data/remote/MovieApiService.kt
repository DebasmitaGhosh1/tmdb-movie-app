package com.debasmita.tmdbmovieapp.data.remote


import com.debasmita.tmdbmovieapp.data.model.CreditsResponse
import com.debasmita.tmdbmovieapp.data.model.MovieDetailResponse
import com.debasmita.tmdbmovieapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MovieResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): CreditsResponse

}

