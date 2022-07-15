package com.oliver.couchpotato.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliver.couchpotato.db.entities.Cast
import com.oliver.couchpotato.db.entities.Movie
import com.oliver.couchpotato.repositories.CastRepository
import com.oliver.couchpotato.repositories.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel
@Inject
constructor(private val movieRepository: MovieRepository): ViewModel() {


}