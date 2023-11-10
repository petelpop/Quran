package com.pall.quranapp.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pall.quranapp.core.di.Injection
import com.pall.quranapp.presentation.quran.QuranViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(val context: Context) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(QuranViewModel::class.java) -> {
                QuranViewModel(Injection.provideQuranRepository()) as T
            }
            modelClass.isAssignableFrom(SharedViewModel::class.java) -> {
                SharedViewModel(context) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
}