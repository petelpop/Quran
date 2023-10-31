package com.pall.quranapp.presentation.quran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider
import com.pall.quranapp.adapter.QuranAdapter
import com.pall.quranapp.databinding.FragmentQuranBinding

class QuranFragment : Fragment() {
    private var _binding : FragmentQuranBinding? = null
    private val binding get() = _binding as FragmentQuranBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuranBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quranViewModel = ViewModelProvider(this)[QuranViewModel::class.java]
        quranViewModel.getListSurah()
        quranViewModel.listSurah.observe(viewLifecycleOwner){
            binding.rvQuran.apply {
                val mAdapter = QuranAdapter()
                mAdapter.setData(it.listSurah)
                adapter = mAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}