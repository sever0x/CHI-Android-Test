package com.shdwraze.chi.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.shdwraze.chi.network.ShibeApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {

    val shibeRepository: ShibeRepository
}

class DefaultAppContainer: AppContainer {
    override val shibeRepository: ShibeRepository by lazy {
        NetworkShibeRepository(retrofitService)
    }

    private val baseUrl = "https://shibe.online/api/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: ShibeApiService by lazy {
        retrofit.create(ShibeApiService::class.java)
    }
}