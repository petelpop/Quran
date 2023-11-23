package com.pall.quranapp.core.data.network.adzan

import retrofit2.http.GET
import retrofit2.http.Path

interface AdzanApiService {

    @GET("sholat/kota/cari/{city}")
    suspend fun searchCity(
        @Path("city") city: String
    ): CityResponse
}