package com.pall.quranapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pall.quranapp.core.data.AdzanRepository
import com.pall.quranapp.core.data.Resource
import com.pall.quranapp.core.domain.DailyAdzanResult

class AdzanViewModel(private val adzanRepository: AdzanRepository): ViewModel() {
    fun getDailyAdzanTime(): LiveData<Resource<DailyAdzanResult>> =
        adzanRepository.getDailyAdzanTime()
}