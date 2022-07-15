package com.oliver.couchpotato.api

import com.oliver.couchpotato.helper.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val api:Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}