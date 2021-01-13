package com.example.paging3demo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    val id: Int,
    val original_title: String,
    val backdrop_path: String,
    val poster_path: String,
    val overview: String,
    val adult: Boolean,
    val release_date: String,
    val original_language: String,
    val popularity: Double,
    val vote_average: Double,
    val vote_count: Int
)