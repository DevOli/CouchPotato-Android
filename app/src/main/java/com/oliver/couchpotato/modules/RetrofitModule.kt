package com.oliver.couchpotato.modules

import com.oliver.couchpotato.api.RetrofitHelper
import com.oliver.couchpotato.api.services.DiscoverService
import com.oliver.couchpotato.api.services.MoviesService
import com.oliver.couchpotato.api.services.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideMovieService(): MoviesService {
        return RetrofitHelper.api.create(MoviesService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(): UserService {
        return RetrofitHelper.api.create(UserService::class.java)
    }

}