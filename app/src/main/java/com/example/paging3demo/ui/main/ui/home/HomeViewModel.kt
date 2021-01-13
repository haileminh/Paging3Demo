package com.example.paging3demo.ui.main.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3demo.data.model.Movie
import com.example.paging3demo.data.repository.Repository
import com.example.paging3demo.data.repository.paged.MoviePagingSource
import com.example.paging3demo.utils.Constants.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val repository: Repository) : ViewModel() {

    val movies: Flow<PagingData<Movie>> =
        Pager(
            PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            )
        ) {
            MoviePagingSource(repository)
        }.flow
            .cachedIn(viewModelScope)
}