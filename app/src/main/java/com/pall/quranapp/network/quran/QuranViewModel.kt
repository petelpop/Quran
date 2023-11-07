package com.pall.quranapp.network.quran

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pall.quranapp.data.QuranRepository
import com.pall.quranapp.data.Resource
import com.pall.quranapp.domain.model.QuranEdition
import com.pall.quranapp.domain.model.Surah
import com.pall.quranapp.network.ApiConfig
import com.pall.quranapp.network.AyahResponse
import com.pall.quranapp.network.SurahResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuranViewModel(private val quranRepository: QuranRepository) : ViewModel() {
    fun getListSurah(): LiveData<Resource<List<Surah>>> =
        quranRepository.getListSurah().asLiveData()

    fun getDetailSurahWithQuranEdition(number: Int): LiveData<Resource<List<QuranEdition>>> =
        quranRepository.getDetailSurahWithQuranEditions(number).asLiveData()
}