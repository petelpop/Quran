package com.pall.quranapp.presentation.quran

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pall.quranapp.network.ApiConfig
import com.pall.quranapp.network.AyahResponse
import com.pall.quranapp.network.SurahResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuranViewModel : ViewModel() {
    private var _listSurah = MutableLiveData<SurahResponse>()
    val listSurah get() = _listSurah as LiveData<SurahResponse>

    private var _listAyah = MutableLiveData<AyahResponse>()
    val listAyah get() = _listAyah as LiveData<AyahResponse>

    fun getListSurah() {
        ApiConfig.quranApiService.getListSurah().enqueue(object : Callback<SurahResponse> {
            override fun onResponse(call: Call<SurahResponse>, response: Response<SurahResponse>) {
                if (response.isSuccessful) {
                    _listSurah.value = response.body()
                } else Log.e(
                    "QuranViewModel",
                    "onResponse : Error call with Http status code" + response.code()
                )
            }
            override fun onFailure(call: Call<SurahResponse>, t: Throwable) {
                Log.e("QuranViewModel", "onFailure: " + t.localizedMessage)
            }
        })
    }

    fun getListAyahBySurah(number: Int) {
        ApiConfig.quranApiService.getListAyahs(number).enqueue(object :
            Callback<AyahResponse> {
            override fun onResponse(
                call: Call<AyahResponse>, response: Response<AyahResponse>) {
                if (response.isSuccessful) {
                    _listAyah.postValue(response.body())
                } else Log.e(
                    "QuranViewModel",
                    "onResponse : Error call with Http status code" + response.code()
                )
            }
            override fun onFailure(call: Call<AyahResponse>, t: Throwable) {
                Log.e("QuranViewModel", "onFailure: " + t.localizedMessage)
            }
        })
    }
}