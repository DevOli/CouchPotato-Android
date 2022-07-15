package com.oliver.couchpotato.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oliver.couchpotato.api.RetrofitHelper
import com.oliver.couchpotato.api.dto.DiscoverResults
import com.oliver.couchpotato.api.services.DiscoverService
import com.oliver.couchpotato.api.services.MoviesService
import com.oliver.couchpotato.db.dao.CastDao
import com.oliver.couchpotato.db.dao.MoviesDao
import com.oliver.couchpotato.db.entities.Cast
import com.oliver.couchpotato.db.entities.Movie
import com.oliver.couchpotato.helper.AUTH_TOKEN
import com.oliver.couchpotato.helper.Extensions.getDateFormatted
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CastRepository (private val moviesService: MoviesService, private val dao: CastDao) {

    suspend fun getCast(movieId: Long): List<Cast>? {

        val result = moviesService.getMovieCast(movieId, AUTH_TOKEN)
        if (result.isSuccessful) {
            val list = result.body()?.cast ?: emptyList()
            val entityList = list.map {
                Cast(
                    it.id,
                    it.name,
                    it.character,
                    it.adult,
                    it.gender,
                    it.originalName,
                    it.popularity,
                    it.profilePath,
                    it.castID,
                    it.creditID,
                    it.order,
                    it.department,
                    it.job
                )
            }
            saveInDB(entityList)
            return entityList.take(10)
        }

        return null
    }

    fun saveInDB(list: List<Cast>) {
        list.forEach {
            dao.insert(it)
        }
    }

    fun deleteTable(): Int {
        return dao.deleteAll()
    }
}