package com.example.paging3demo.data.repository

import com.example.paging3demo.data.model.Result
import com.example.paging3demo.data.model.response.MovieListResponse
import com.example.paging3demo.data.network.MovieAppService

class Repository(private val service: MovieAppService) {
    suspend fun getPopularMovies(page: Int): MovieListResponse {
        return when (val result = service.fetchPopularMovies(page)) {
            is Result.Success -> result.data
            is Result.Error -> throw result.error
        }
    }
}