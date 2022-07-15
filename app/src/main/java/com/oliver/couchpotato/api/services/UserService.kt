package com.oliver.couchpotato.api.services

import com.oliver.couchpotato.api.dto.CastResults
import com.oliver.couchpotato.api.dto.MovieResult
import com.oliver.couchpotato.api.dto.UserResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("account")
    suspend fun getMovieDetail(
        @Query("session_id") sessionId: String,
        @Header("Authorization") authorization: String,
    ): Response<UserResult>

}