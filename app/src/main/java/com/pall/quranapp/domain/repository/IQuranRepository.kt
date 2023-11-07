package com.pall.quranapp.domain.repository

import com.pall.quranapp.data.Resource
import com.pall.quranapp.domain.model.QuranEdition
import com.pall.quranapp.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {
    fun getListSurah(): Flow<Resource<List<Surah>>>

    fun getDetailSurahWithQuranEditions(number: Int): Flow<Resource<List<QuranEdition>>>

}