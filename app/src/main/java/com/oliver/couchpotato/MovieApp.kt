package com.oliver.couchpotato

import android.app.Application
import com.oliver.couchpotato.db.MovieDB
import com.oliver.couchpotato.repositories.MovieRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApp: Application() {

    private val movieDB by lazy {
        MovieDB.create(this)
    }

    val movieRepo by lazy {
        MovieRepository(movieDB.moviesDao())
    }
}