package com.example.paging3demo.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3demo.data.model.Movie
import com.example.paging3demo.databinding.MovieItemBinding

class MovieListAdapter : PagingDataAdapter<Movie, RecyclerView.ViewHolder>(MovieComparator) {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val movie: Movie = getItem(position)!!

        val viewHolder = holder as MovieViewHolder
        viewHolder.movieItemBinding.movieTitle.text = movie.original_title
        viewHolder.movieItemBinding.movieVoteCount.text = "Vote count ${movie.vote_count}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            MovieItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class MovieViewHolder(val movieItemBinding: MovieItemBinding) :
        RecyclerView.ViewHolder(movieItemBinding.root)

    object MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}