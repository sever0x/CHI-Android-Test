package com.shdwraze.chi.data

import com.shdwraze.chi.network.ShibeApiService

interface ShibeRepository {

    suspend fun getShibePhotos(): List<String>
}

class NetworkShibeRepository(
    private val shibeApiService: ShibeApiService
): ShibeRepository {
    override suspend fun getShibePhotos(): List<String> = shibeApiService.getPhotos()
}