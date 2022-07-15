package com.oliver.couchpotato.api.services

import com.oliver.couchpotato.api.dto.DiscoverResults
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface DiscoverService {

    @GET("discover/movie?language=en-US&sort_by=primary_release_date.desc&include_adult=false&page=1")
    fun discoverNew(
        @Header("Authorization") authorization: String,
        @Query("primary_release_date.lte") date: String
    ): Call<DiscoverResults>

    @GET("discover/movie?language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    fun discoverPopular(
        @Header("Authorization") authorization: String
    ): Call<DiscoverResults>

    @GET("account/11821266/favorite/movies?session_id=73c1d43852fc69fa9d51220abf0ceb76df2ee7f0")
    suspend fun getFavoriteMovies(
        @Header("Authorization") authorization: String,
    ): Response<DiscoverResults>
}