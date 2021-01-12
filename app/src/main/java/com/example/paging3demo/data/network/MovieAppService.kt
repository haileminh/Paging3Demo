package com.example.paging3demo.data.network

import com.example.paging3demo.data.model.Result
import com.example.paging3demo.data.model.response.MovieListResponse

class MovieAppService(private val api: Api) : BaseService() {
    suspend fun fetchPopularMovies(page: Int): Result<MovieListResponse> {
        return createCall { api.getPopularMovies(page) }
    }
}