package com.pall.quranapp.data

import android.util.Log
import com.pall.quranapp.network.QuranApiService
import com.pall.quranapp.network.QuranEditionItem
import com.pall.quranapp.network.SurahItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class QuranRemoteDataSource(private val apiService: QuranApiService) {
    suspend fun getListSurah(): Flow<NetworkResponse<List<SurahItem>>> =
        flow {
            try {
                val surahResponse = apiService.getListSurah() // SurahResponse
                val listSurah = surahResponse.listSurah
                emit(NetworkResponse.Success(listSurah))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(QuranRemoteDataSource::class.java.simpleName, "error :" + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailSurahWithQuranEdition(number: Int): Flow<NetworkResponse<List<QuranEditionItem>>> =
        flow {
            try {
                val ayahResource = apiService.getDetailSurahWithQuranEditions(number)
                val quranEdition = ayahResource.quranEditionItem
                emit(NetworkResponse.Success(quranEdition))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(QuranRemoteDataSource::class.java.simpleName, "error :" + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)
}