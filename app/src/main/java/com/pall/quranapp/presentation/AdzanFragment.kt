package com.pall.quranapp.presentation

import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.pall.quranapp.R
import com.pall.quranapp.databinding.FragmentAdzanBinding
import java.util.Locale

class AdzanFragment : Fragment() {
    private var _binding: FragmentAdzanBinding? = null
    private val binding get() = _binding as FragmentAdzanBinding
    private val sharedViewModel: SharedViewModel by activityViewModels {
        ViewModelFactory(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdzanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.getKnownLastLocation()
        sharedViewModel.lastKnownLocation.observe(viewLifecycleOwner) {
            if (it != null) {
                val geocoder = Geocoder(requireContext(), Locale.getDefault())
                geocoder.getFromLocation(
                    it[0],
                    it[1],
                    1
                ) { listAddress ->
                    val city = listAddress[0].subAdminArea
                    val resultOfCity = city.split(" ")
                    binding.tvLocation.text = resultOfCity[1]
                }
            } else {
                Toast.makeText(context, "Sorry something wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}