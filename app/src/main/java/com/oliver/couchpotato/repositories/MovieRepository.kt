package com.oliver.couchpotato.repositories

import com.oliver.couchpotato.api.dto.DiscoverResults
import com.oliver.couchpotato.api.RetrofitHelper
import com.oliver.couchpotato.api.dto.MovieResult
import com.oliver.couchpotato.api.services.DiscoverService
import com.oliver.couchpotato.api.services.MoviesService
import com.oliver.couchpotato.db.dao.MoviesDao
import com.oliver.couchpotato.db.entities.Movie
import com.oliver.couchpotato.helper.AUTH_TOKEN
import com.oliver.couchpotato.helper.Extensions.getDateFormatted
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MovieRepository(private val dao: MoviesDao) {

    val popMovieList = dao.getPopular()
    val newMovieList = dao.getNew()

    fun getNewMovies(onSuccess:()->Unit, onFail:()->Unit) {

        val now = Date().getDateFormatted()

        RetrofitHelper.api.create(DiscoverService::class.java)
            .discoverNew(AUTH_TOKEN, now).enqueue(object : Callback<DiscoverResults> {
            override fun onResponse(call: Call<DiscoverResults>, response: Response<DiscoverResults>) {
                if (response.isSuccessful) {
                    val list = response.body()?.results ?: listOf()
                    saveInDB(list.map { Movie(
                        id = it.id,
                        adult = it.adult,
                        backdropPath = it.backdropPath,
                        //it.genreIDS,
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
                        group = Movie.MovieGroup.NEW
                    ) })

                    onSuccess()
                }
            }

            override fun onFailure(call: Call<DiscoverResults>, t: Throwable) {
                onFail()
            }

        })
    }

    fun getPopularMovies(onSuccess:()->Unit, onFail:()->Unit) {

        RetrofitHelper.api.create(DiscoverService::class.java)
            .discoverPopular(AUTH_TOKEN).enqueue(object : Callback<DiscoverResults> {
                override fun onResponse(call: Call<DiscoverResults>, response: Response<DiscoverResults>) {
                    if (response.isSuccessful) {
                        val list = response.body()?.results ?: listOf()
                        saveInDB(list.map { Movie(
                            id = it.id,
                            adult = it.adult,
                            backdropPath = it.backdropPath,
                            //it.genreIDS,
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

                            group = Movie.MovieGroup.POPULAR
                        ) })

                        onSuccess()
                    }
                }

                override fun onFailure(call: Call<DiscoverResults>, t: Throwable) {
                    onFail()
                }

            })
    }

    suspend fun getMovieById(movieId: Long): Response<MovieResult> {
        return RetrofitHelper.api.create(MoviesService::class.java)
            .getMovieDetail(movieId, AUTH_TOKEN)
    }

    suspend fun getFavoriteMovie(): Response<DiscoverResults> {
        return RetrofitHelper.api.create(DiscoverService::class.java)
            .getFavoriteMovies(AUTH_TOKEN)
    }

    fun saveInDB(list: List<Movie>) {
        list.forEach {
            dao.insert(it)
        }
    }

    fun deleteTable(): Int {
        return dao.deleteAll()
    }
}