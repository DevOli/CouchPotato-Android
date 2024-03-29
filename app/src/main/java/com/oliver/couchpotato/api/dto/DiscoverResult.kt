package com.oliver.couchpotato.api.dto

import com.google.gson.annotations.SerializedName

data class DiscoverResults (
    val page: Long? = null,
    val results: List<Result>? = null,

    @SerializedName("total_pages")
    val totalPages: Long? = null,

    @SerializedName("total_results")
    val totalResults: Long? = null
)

data class Result (
    val id: Long,
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("genre_ids")
    val genreIDS: List<Long>? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,

    val overview: String? = null,
    val popularity: Double? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    val title: String? = null,
    val video: Boolean? = null,

    @SerializedName("vote_average")
    val voteAverage: Double? = null,

    @SerializedName("vote_count")
    val voteCount: Long? = null
)




