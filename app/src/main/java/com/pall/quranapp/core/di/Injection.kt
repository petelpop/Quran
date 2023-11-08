package com.pall.quranapp.core.di

import com.pall.quranapp.core.data.QuranRemoteDataSource
import com.pall.quranapp.core.data.QuranRepository
import com.pall.quranapp.core.network.ApiConfig

object Injection {
    fun provideQuranRepository(): QuranRepository {
        val quranApiService = ApiConfig.quranApiService
        val quranRemoteDataSource = QuranRemoteDataSource(quranApiService)
        return QuranRepository(quranRemoteDataSource)
    }
}