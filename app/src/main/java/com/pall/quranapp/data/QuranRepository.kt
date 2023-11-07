package com.pall.quranapp.data

import android.provider.ContactsContract.Data
import com.pall.quranapp.domain.model.QuranEdition
import com.pall.quranapp.domain.model.Surah
import com.pall.quranapp.domain.repository.IQuranRepository
import com.pall.quranapp.network.QuranEditionItem
import com.pall.quranapp.network.SurahItem
import com.pall.quranapp.utils.DataMapper
import kotlinx.coroutines.flow.Flow

class QuranRepository(private val remoteDataSource: QuranRemoteDataSource) : IQuranRepository {
    override fun getListSurah(): Flow<Resource<List<Surah>>> {
        return object : NetworkBoundResource<List<Surah>, List<SurahItem>>() {
            override fun fetchFromNetwork(data: List<SurahItem>): Flow<List<Surah>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<SurahItem>>> {
                return remoteDataSource.getListSurah()
            }
        }.asFlow()
    }

    override fun getDetailSurahWithQuranEditions(number: Int): Flow<Resource<List<QuranEdition>>> {
        return object : NetworkBoundResource<List<QuranEdition>, List<QuranEditionItem>>() {
            override fun fetchFromNetwork(data: List<QuranEditionItem>): Flow<List<QuranEdition>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<QuranEditionItem>>> {
                return remoteDataSource.getDetailSurahWithQuranEdition(number)
            }

        }.asFlow()
    }

}