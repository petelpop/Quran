package com.pall.quranapp.core.domain

import com.pall.quranapp.core.data.Resource
import com.pall.quranapp.core.domain.model.City

data class DailyAdzanResult(
    val listLocation: List<String>,
    val listCity: Resource<List<City>>
)
