package com.oliver.couchpotato.api.services

import com.oliver.couchpotato.api.dto.DiscoverResults
import com.oliver.couchpotato.api.dto.UserResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FavoritesService {

    @GET("account/11821266/favorite/movies?session_id=73c1d43852fc69fa9d51220abf0ceb76df2ee7f0")
    suspend fun getFavoriteMovies(
        @Header("Authorization") authorization: String,
    ): Response<DiscoverResults>

}
