package com.oliver.couchpotato.modules

import android.content.Context
import com.oliver.couchpotato.db.MovieDB
import com.oliver.couchpotato.db.dao.CastDao
import com.oliver.couchpotato.db.dao.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun proveDB(@ApplicationContext context: Context): MovieDB {
        return MovieDB.create(context)
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDB): MoviesDao {
        return database.moviesDao()
    }

    @Singleton
    @Provides
    fun provideCastDao(database: MovieDB): CastDao {
        return database.castDao()
    }

}