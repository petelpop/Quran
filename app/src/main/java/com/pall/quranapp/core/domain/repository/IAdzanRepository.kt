package com.pall.quranapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.pall.quranapp.core.data.Resource
import com.pall.quranapp.core.domain.model.City
import kotlinx.coroutines.flow.Flow

interface IAdzanRepository {

    fun getLocation(): LiveData<List<String>>
    fun searchCity(city: String): Flow<Resource<List<City>>>
}