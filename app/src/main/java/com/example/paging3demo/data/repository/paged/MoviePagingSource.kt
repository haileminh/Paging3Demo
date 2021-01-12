package com.example.paging3demo.data.repository.paged

import androidx.paging.PagingSource
import com.example.paging3demo.data.model.Movie
import com.example.paging3demo.data.repository.Repository
import com.example.paging3demo.utils.printLog
import java.lang.Exception

class MoviePagingSource(private val repository: Repository) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPage = params.key ?: 1
            printLog("nextPage ==> $nextPage")
            val movieListResponse = repository.getPopularMovies(nextPage)

            LoadResult.Page(
                    data = movieListResponse.results!!,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (nextPage < movieListResponse.totalPages!!)
                        movieListResponse.page?.plus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}