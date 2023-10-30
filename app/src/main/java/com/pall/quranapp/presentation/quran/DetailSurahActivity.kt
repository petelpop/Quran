package com.pall.quranapp.presentation.quran

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pall.quranapp.network.SurahItem
import com.pall.quranapp.adapter.SurahAdapter
import com.damarazka.quran.databinding.ActivityDetailSurahBinding
import com.damarazka.quran.databinding.CustomViewAlertDialogBinding
import com.pall.quranapp.network.AyahsItem


class DetailSurahActivity : AppCompatActivity() {
    private var _binding: ActivityDetailSurahBinding? = null
    private val binding get() = _binding as ActivityDetailSurahBinding

    private var _surah: SurahItem? = null
    private val surah get() = _surah as SurahItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _surah = intent.getParcelableExtra(EXTRA_DATA, SurahItem::class.java)

        initView()
        val mAdapter = SurahAdapter()
        mAdapter.setOnItemClicked(object : SurahAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: AyahsItem) {
                showAlertDialog()
            }

        })

        val quranViewModel = ViewModelProvider(this)[QuranViewModel::class.java]
        surah.number?.let { quranViewModel.getListAyahBySurah(it) }
        quranViewModel.listAyah.observe(this) {
            mAdapter.setData(it.quranEdition?.get(0)?.ayahs, it.quranEdition)
            binding.rvSurah.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(this@DetailSurahActivity)
            }
        }
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        val view = CustomViewAlertDialogBinding.inflate(layoutInflater)
        builder.setView(view.root)
        builder.show()
    }
    private fun initView() {
        binding.apply {
            tvDetailSurah.text = surah.englishName
            tvDetailNameTranslation.text = surah.englishNameTranslation
            val revelationSurah = surah.revelationType
            val numberAyahs = surah.numberOfAyahs
            val resultAyah = "$revelationSurah - $numberAyahs"
            tvDetailAyah.text = resultAyah
            tvDetailName.text = surah.name
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}