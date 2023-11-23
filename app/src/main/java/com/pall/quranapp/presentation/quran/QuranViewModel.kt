package com.pall.quranapp.presentation.quran

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pall.quranapp.core.data.QuranRepository
import com.pall.quranapp.core.data.Resource
import com.pall.quranapp.core.domain.model.QuranEdition
import com.pall.quranapp.core.domain.model.Surah


class QuranViewModel(private val quranRepository: QuranRepository) : ViewModel() {
    fun getListSurah(): LiveData<Resource<List<Surah>>> =
        quranRepository.getListSurah().asLiveData()

    fun getDetailSurahWithQuranEdition(number: Int): LiveData<Resource<List<QuranEdition>>> =
        quranRepository.getDetailSurahWithQuranEditions(number).asLiveData()
}