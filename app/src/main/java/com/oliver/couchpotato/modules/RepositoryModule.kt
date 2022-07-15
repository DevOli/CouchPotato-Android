package com.oliver.couchpotato.modules

import com.oliver.couchpotato.api.services.MoviesService
import com.oliver.couchpotato.api.services.UserService
import com.oliver.couchpotato.db.dao.CastDao
import com.oliver.couchpotato.db.dao.MoviesDao
import com.oliver.couchpotato.repositories.CastRepository
import com.oliver.couchpotato.repositories.MovieRepository
import com.oliver.couchpotato.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(movieDao: MoviesDao):MovieRepository {
        return MovieRepository(movieDao)
    }

    @Singleton
    @Provides
    fun provideCastRepository(moviesService: MoviesService, castDao: CastDao): CastRepository {
        return CastRepository(moviesService, castDao)
    }

    @Singleton
    @Provides
    fun provideUserRepository(userService: UserService): UserRepository {
        return UserRepository(userService)
    }
}