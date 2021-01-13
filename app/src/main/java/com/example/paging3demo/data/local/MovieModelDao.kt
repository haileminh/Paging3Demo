package com.example.paging3demo.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paging3demo.data.model.Movie

@Dao
interface MovieModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieModel: List<Movie>)

    @Query("SELECT * FROM movie")
    fun getAllMovieModel(): PagingSource<Int, Movie>

    @Query("DELETE FROM Movie")
    suspend fun clearAllMovie()

}