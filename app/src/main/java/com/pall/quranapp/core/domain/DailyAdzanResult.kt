package com.pall.quranapp.core.domain

import com.pall.quranapp.core.data.Resource
import com.pall.quranapp.core.domain.model.City
import com.pall.quranapp.core.domain.model.Jadwal

data class DailyAdzanResult(
    val listLocation: List<String>,
    val adzanTime: Resource<Jadwal>,
    val currentDate: List<String>
)
