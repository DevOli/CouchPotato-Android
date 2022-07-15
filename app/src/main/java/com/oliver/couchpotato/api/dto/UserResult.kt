package com.oliver.couchpotato.api.dto

import com.google.gson.annotations.SerializedName

data class UserResult (
    val avatar: Avatar? = null,
    val id: Long? = null,

    @SerializedName("iso_639_1")
    val iso639_1: String? = null,

    @SerializedName("iso_3166_1")
    val iso3166_1: String? = null,

    val name: String? = null,

    @SerializedName("include_adult")
    val includeAdult: Boolean? = null,

    val username: String? = null
)

data class Avatar (
    val gravatar: Gravatar? = null,
    val tmdb: Tmdb? = null
)

data class Gravatar (
    val hash: String? = null
)

data class Tmdb (
    @SerializedName("avatar_path")
    val avatarPath: String? = null
)
