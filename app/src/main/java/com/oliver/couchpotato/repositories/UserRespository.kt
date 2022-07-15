package com.oliver.couchpotato.repositories

import com.oliver.couchpotato.api.dto.MovieResult
import com.oliver.couchpotato.api.dto.UserResult
import com.oliver.couchpotato.api.services.MoviesService
import com.oliver.couchpotato.api.services.UserService
import com.oliver.couchpotato.helper.AUTH_TOKEN
import com.oliver.couchpotato.helper.SESSION_TOKEN
import retrofit2.Response

class UserRepository(private val userService: UserService) {

    suspend fun getUserDetails(): Response<UserResult> {
        return userService.getMovieDetail(SESSION_TOKEN, AUTH_TOKEN)
    }
}