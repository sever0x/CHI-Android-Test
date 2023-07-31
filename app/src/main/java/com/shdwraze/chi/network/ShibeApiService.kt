package com.shdwraze.chi.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ShibeApiService {

    @GET("shibes")
    suspend fun getPhotos(
        @Query("count") count: Int = 10,
        @Query("urls") urls: Boolean = true,
        @Query("httpsUrls") httpsUrls: Boolean = true
    ): List<String>
}