package com.example.paging3demo.ui.main.ui.separators

import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.example.paging3demo.data.model.Movie
import com.example.paging3demo.data.repository.Repository
import com.example.paging3demo.data.repository.paged.MoviePagingSource
import com.example.paging3demo.utils.Constants.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DashboardViewModel(private val repository: Repository) : ViewModel() {

    val movies: Flow<PagingData<MovieModel>> = getMovieListStream()
        .map { pagingData -> pagingData.map { MovieModel.MovieItem(it) } }
        .map {
            it.insertSeparators<MovieModel.MovieItem, MovieModel> { before, after ->

                if (after == null) {
                    // we're at the end of the list
                    return@insertSeparators MovieModel.SeparatorItem("End of list")
                }

                if (before == null) {
                    // we're at the beginning of the list
                    return@insertSeparators null//MovieModel.SeparatorItem("${after.roundedVoteCount}0.000+ stars")
                }
                // check between 2 items
                if (before.roundedVoteCount > after.roundedVoteCount) {
                    if (after.roundedVoteCount >= 1) {
                        MovieModel.SeparatorItem("Less than ${before.roundedVoteCount}0000 vote count")
                    } else {
                        MovieModel.SeparatorItem("Less than 10000 vote count")
                    }
                } else {
                    // no separator
                    null
                }
            }
        }

    private fun getMovieListStream(): Flow<PagingData<Movie>> {
        return Pager(PagingConfig(DEFAULT_PAGE_SIZE)) {
            MoviePagingSource(repository)
        }.flow
    }
}

sealed class MovieModel {
    data class MovieItem(val movie: Movie) : MovieModel()
    data class SeparatorItem(val description: String) : MovieModel()
}

private val MovieModel.MovieItem.roundedVoteCount: Int
    get() = this.movie.vote_count / 1000

