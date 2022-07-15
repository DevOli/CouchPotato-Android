package com.oliver.couchpotato.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oliver.couchpotato.db.entities.Cast
import com.oliver.couchpotato.db.entities.Movie

@Dao
interface CastDao {

    @Query("SELECT * FROM `Cast` WHERE id = :id LIMIT 10")
    fun getActorBy(id: Long): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: Cast)

    @Query("DELETE FROM `Cast`")
    fun deleteAll(): Int
}