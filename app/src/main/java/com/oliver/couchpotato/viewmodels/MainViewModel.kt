package com.oliver.couchpotato.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliver.couchpotato.db.entities.Cast
import com.oliver.couchpotato.db.entities.Movie
import com.oliver.couchpotato.repositories.CastRepository
import com.oliver.couchpotato.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val movieRepository: MovieRepository,
            private val castRepository: CastRepository
): ViewModel() {

    private var _isLoadingPop: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingPop: LiveData<Boolean> = _isLoadingPop

    private var _isLoadingNew: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingNew: LiveData<Boolean> = _isLoadingNew

    val popularMovieList: LiveData<List<Movie>> = movieRepository.popMovieList
    val newMovieList: LiveData<List<Movie>> = movieRepository.newMovieList

    private val _castList: MutableLiveData<List<Cast>> = MutableLiveData()
    val castList: LiveData<List<Cast>> = _castList

    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie> = _movie

    fun getPopularMovies() {
        _isLoadingPop.value = true
        movieRepository.getPopularMovies({
            _isLoadingPop.value = false
        }, {
            _isLoadingPop.value = false
        })
    }

    fun getNewMovies() {
        _isLoadingNew.value = true
        movieRepository.getNewMovies({
            _isLoadingNew.value = false
        }, {
            _isLoadingNew.value = false
        })
    }

    fun getMovieById(movieId: Long) = viewModelScope.launch() {
        try {
            val result = movieRepository.getMovieById(movieId)
            if (result.isSuccessful){
                val movieResponse = result.body()
                movieResponse?.also {
                    val movieEntity = Movie(
                        it.id,
                        it.adult,
                        it.backdropPath,
                        it.originalLanguage,
                        it.originalTitle,
                        it.overview,
                        it.popularity,
                        it.posterPath,
                        it.releaseDate,
                        it.title,
                        it.video,
                        it.voteAverage,
                        it.voteCount,
                        it.runtime,
                        null
                        )
                    _movie.postValue(movieEntity)
                }
            }
        } catch (e:Exception){
            Log.e("ErrorOliver", e.toString())
        }
    }

    fun getCastbyMovieId(movieId: Long) = viewModelScope.launch {
        try {
            val cast = castRepository.getCast(movieId)
            cast?.also {
                _castList.postValue(it)
            } ?: run {
                // Error
            }
        } catch (e: Exception) {
            Log.e("ErrorOliver", e.toString())
        }
    }

    fun deleteTable(): Int {
        return movieRepository.deleteTable()
    }
}