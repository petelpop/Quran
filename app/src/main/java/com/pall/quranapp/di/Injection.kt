package com.pall.quranapp.di

import com.pall.quranapp.data.QuranRemoteDataSource
import com.pall.quranapp.data.QuranRepository
import com.pall.quranapp.network.ApiConfig

object Injection {
    fun provideQuranRepository(): QuranRepository {
        val quranApiService = ApiConfig.quranApiService
        val quranRemoteDataSource = QuranRemoteDataSource(quranApiService)
        return QuranRepository(quranRemoteDataSource)
    }
}