package com.debasmita.tmdbmovieapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.debasmita.tmdbmovieapp.data.model.Movie

class SearchMoviePagingSource(
    private val api: MovieApiService,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = api.searchMovies(query, page)

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = null
}
