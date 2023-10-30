package com.pall.quranapp.network

import retrofit2.Call
import retrofit2.http.GET

interface QuranApiService {
    @GET("surah")
    fun getListSurah() : Call<SurahResponse>
}