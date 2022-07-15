package com.oliver.couchpotato.api.dto

import com.google.gson.annotations.SerializedName


data class CastResults (
    val id: Long? = null,
    val cast: List<Cast>? = null,
    val crew: List<Cast>? = null
)

data class Cast (
    val id: Long,
    val name: String,
    val character: String,
    val adult: Boolean? = null,
    val gender: Long? = null,

    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,

    @SerializedName("original_name")
    val originalName: String? = null,

    val popularity: Double? = null,

    @SerializedName("profile_path")
    val profilePath: String? = null,

    @SerializedName("cast_id")
    val castID: Long? = null,

    @SerializedName("credit_id")
    val creditID: String? = null,

    val order: Long? = null,
    val department: String? = null,
    val job: String? = null
)