package com.pall.quranapp.core.domain.repository

import com.pall.quranapp.core.data.Resource
import com.pall.quranapp.core.domain.model.QuranEdition
import com.pall.quranapp.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {
    fun getListSurah(): Flow<Resource<List<Surah>>>

    fun getDetailSurahWithQuranEditions(number: Int): Flow<Resource<List<QuranEdition>>>

}