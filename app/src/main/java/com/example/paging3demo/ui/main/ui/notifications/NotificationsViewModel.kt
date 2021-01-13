package com.example.paging3demo.ui.main.ui.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.paging3demo.data.local.AppDatabase
import com.example.paging3demo.data.local.LocalInjector
import com.example.paging3demo.data.model.Movie
import com.example.paging3demo.data.repository.Repository
import com.example.paging3demo.data.repository.paged.MovieMediator
import com.example.paging3demo.data.repository.paged.MoviePagingSource
import com.example.paging3demo.utils.Constants
import kotlinx.coroutines.flow.Flow

class NotificationsViewModel(private val repository: Repository) : ViewModel() {
    private val appDatabase = LocalInjector.injectDb()

    private val pagingSourceFactory = { appDatabase!!.getMovieModelDao().getAllMovieModel() }

    @ExperimentalPagingApi
    val movies: Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = MovieMediator(repository, appDatabase!!)
        ).flow.cachedIn(viewModelScope)
}