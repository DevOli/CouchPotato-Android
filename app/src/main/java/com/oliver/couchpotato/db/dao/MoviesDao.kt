package com.oliver.couchpotato.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oliver.couchpotato.db.entities.Movie

@Dao
interface MoviesDao {

    @Query("SELECT * FROM Movies")
    fun getAll(): LiveData<List<Movie>>

    @Query("SELECT * FROM Movies WHERE `group` = 'POPULAR' AND posterPath IS NOT NULL LIMIT 10")
    fun getPopular(): LiveData<List<Movie>>

    @Query("SELECT * FROM Movies WHERE posterPath IS NOT NULL ORDER BY releaseDate DESC LIMIT 10")
    fun getNew(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: Movie)

    @Query("DELETE FROM Movies")
    fun deleteAll(): Int
}