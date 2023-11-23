package com.pall.quranapp.presentation

import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.pall.quranapp.R
import com.pall.quranapp.core.data.Resource
import com.pall.quranapp.databinding.FragmentAdzanBinding
import java.util.Locale

class AdzanFragment : Fragment() {
    private var _binding: FragmentAdzanBinding? = null
    private val binding get() = _binding as FragmentAdzanBinding

    private val adzanViewModel: AdzanViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdzanBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adzanViewModel.getDailyAdzanTime().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    binding.tvLocation.text = it.data?.listLocation?.get(1)

                    when (val listCity = it.data?.listCity) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            val idCity = listCity.data?.get(0)?.id
                            val city = listCity.data?.get(0)?.lokasi
                            Log.i("AdzanFragment", "onViewCreated: $city $idCity")
                            Toast.makeText(context, "id city of $city: $idCity", Toast.LENGTH_SHORT)
                                .show()
                        }

                        is Resource.Error -> {
                            Log.e("AdzanFragment", "getting id city: ${it.message}")
                        }

                        else -> {
                            Toast.makeText(context, "Something wrong.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    it.data?.listCity?.data?.get(0)?.id
                }

                is Resource.Error -> {}
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}