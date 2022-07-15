package com.oliver.couchpotato.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cast")
data class Cast(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val character: String,
    val adult: Boolean? = null,
    val gender: Long? = null,
    val originalName: String? = null,
    val popularity: Double? = null,
    val profilePath: String? = null,
    val castID: Long? = null,
    val creditID: String? = null,
    val order: Long? = null,
    val department: String? = null,
    val job: String? = null
)