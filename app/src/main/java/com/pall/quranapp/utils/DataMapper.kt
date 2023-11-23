package com.pall.quranapp.utils

import com.pall.quranapp.core.domain.model.Ayah
import com.pall.quranapp.core.domain.model.City
import com.pall.quranapp.core.domain.model.QuranEdition
import com.pall.quranapp.core.domain.model.Surah
import com.pall.quranapp.core.data.network.quran.AyahsItem
import com.pall.quranapp.core.data.network.quran.QuranEditionItem
import com.pall.quranapp.core.data.network.quran.SurahItem
import com.pall.quranapp.core.data.network.adzan.CityItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {

    @JvmName("mapSurahItemToSurah")
    fun mapResponseToDomain(input: List<SurahItem>) : Flow<List<Surah>> {
        val listSurah = ArrayList<Surah>()
        input.map {
            val surah = Surah(
                number = it.number,
                englishName = it.englishName,
                numberOfAyahs = it.numberOfAyahs,
                revelationType = it.revelationType,
                name = it.name,
                englishNameTranslation = it.englishNameTranslation
            )
            listSurah.add(surah)
        }
        return flowOf(listSurah)
    }

    @JvmName("mapQuranEditionItemToQuranEdition")
    fun mapResponseToDomain(input: List<QuranEditionItem>) : Flow<List<QuranEdition>> {
        val quranEdition = ArrayList<QuranEdition>()
        input.map {
            val edition = QuranEdition(
                number = it.number,
                englishName = it.englishName,
                numberOfAyahs = it.numberOfAyahs,
                revelationType = it.revelationType,
                name = it.name,
                englishNameTranslation = it.englishNameTranslation,
                ayahs = mapAyahsItemToDomain(it.ayahs)
            )
            quranEdition.add(edition)
        }
        return flowOf(quranEdition)
    }

    private fun mapAyahsItemToDomain(input: List<AyahsItem>) : List<Ayah> {
        val listAyah = ArrayList<Ayah>()
        input.map {
            val ayah = Ayah(
                number = it.number,
                text = it.text,
                numberInSurah = it.numberInSurah,
                audio = it.audio
            )
            listAyah.add(ayah)
        }
        return listAyah
    }

    @JvmName("mapCityItemToDomain")
    fun mapResponseToDomain(input: List<CityItem>):Flow<List<City>> {
        val cities = ArrayList<City>()
        input.map {
            val city = City(
                id = it.id,
                lokasi = it.lokasi
            )
            cities.add(city)
        }
        return flowOf(cities)
    }
}