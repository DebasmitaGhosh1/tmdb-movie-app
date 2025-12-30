package com.debasmita.tmdbmovieapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import com.debasmita.tmdbmovieapp.data.model.Movie
import androidx.paging.PagingState

class MoviePagingSource(
    private val api: MovieApiService
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = api.getMovies(page)

            Log.d("PagingSource", "--Movies loaded: ${response.results.size}")

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            Log.e("PagingSource", "--M Error loading movies", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = null
}

