package com.pall.quranapp.core.data.network.adzan

import retrofit2.http.GET
import retrofit2.http.Path
import java.time.LocalDate
import java.time.Month
import java.time.Year

interface AdzanApiService {

    @GET("sholat/kota/cari/{city}")
    suspend fun searchCity(
        @Path("city") city: String
    ): CityResponse

    @GET("sholat/jadwal/{idCity}/{year}/{month}/{date}")
    suspend fun getDailyAdzanTime(
        @Path("idCity") id: String,
        @Path("year") year: String,
        @Path("month") month: String,
        @Path("date") date: String
    ): DailyResponse
}