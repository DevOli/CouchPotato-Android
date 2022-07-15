package com.oliver.couchpotato.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oliver.couchpotato.repositories.CastRepository
import com.oliver.couchpotato.repositories.MovieRepository

//class MainViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return MainViewModel(repository) as T
//    }
//}