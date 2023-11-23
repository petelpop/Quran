package com.pall.quranapp.core.di

import android.content.Context
import com.pall.quranapp.core.data.AdzanRepository
import com.pall.quranapp.core.data.network.RemoteDataSource
import com.pall.quranapp.core.data.QuranRepository
import com.pall.quranapp.core.data.local.LocationPreferences
import com.pall.quranapp.core.data.network.ApiConfig

object Injection {
    private val quranApiService = ApiConfig.quranApiService
    private val adzanApiService = ApiConfig.adzanApiService
    private val remoteDataSource = RemoteDataSource(quranApiService, adzanApiService)
    fun provideQuranRepository(): QuranRepository = QuranRepository(remoteDataSource)


    fun provideAdzanRepository(context: Context): AdzanRepository {
        val locationPreferences = LocationPreferences(context)
        return AdzanRepository(remoteDataSource, locationPreferences)
    }
}