package com.oliver.couchpotato.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oliver.couchpotato.db.dao.CastDao
import com.oliver.couchpotato.db.dao.MoviesDao
import com.oliver.couchpotato.db.entities.Cast
import com.oliver.couchpotato.db.entities.Movie

@Database(entities = [
    Movie::class, Cast::class]
    , version = 5)
abstract class MovieDB: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun castDao(): CastDao

    companion object {

        @Volatile private var INSTANCE: MovieDB? = null

        fun create(context: Context): MovieDB {
            return INSTANCE ?: synchronized(this){
                val instance = Room
                    .databaseBuilder(context, MovieDB::class.java, "CouchPotatoDB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}