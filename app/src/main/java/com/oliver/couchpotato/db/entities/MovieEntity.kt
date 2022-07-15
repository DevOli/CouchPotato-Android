package com.oliver.couchpotato.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    //val genreIDS: List<Long>,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Long? = null,
    val runtime: Long? = null,

    val group: MovieGroup?
) {
    enum class MovieGroup {
        POPULAR, NEW
    }
}
