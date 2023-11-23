package com.pall.quranapp.core.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices
import java.util.Locale

class LocationPreferences(val context: Context) {
    private val fusedLocation = LocationServices.getFusedLocationProviderClient(context)


    @SuppressLint("MissingPermission")
    fun getKnownLastLocation(): LiveData<List<String>> {
        val lastKnownLocation = MutableLiveData<List<String>>()
        fusedLocation.lastLocation.addOnSuccessListener {
            Log.i("LocPref", "lastloc: $it")
            if (it != null) {
                val geocoder = Geocoder(context, Locale.getDefault())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    geocoder.getFromLocation(
                        -6.4802472,
                        106.82325,
                        1
                    ) { listAddress ->
                        Log.i("LocPref", "getKnownLastLocation: ${listAddress[0].subAdminArea}")
                        val city = listAddress[0].subAdminArea
                        val resultOfCity = city.split(" ")
                        //resultOfCity indo = [Kabupaten, Bogor]
                        //resultOfCity english = [Bogor, Regency]
                        val subLocality = listAddress[0].subLocality
                        val locality = listAddress[0].locality
                        val resultLocation = "$subLocality.$locality"
                        Log.i("LocPref", "getKnownLastLocation: $resultOfCity")

                        val listCity = listOf(resultOfCity[1], resultLocation)
                        lastKnownLocation.postValue(listCity)
                    }
                } else {
                    val listAddress = geocoder.getFromLocation(
                        -6.4802472,
                        106.82325,
                        1
                    )
                    val city = listAddress?.get(0)?.subAdminArea
                    val resultOfCity = city?.split(" ")
                    //resultOfCity indo = [Kabupaten, Bogor]
                    //resultOfCity english = [Bogor, Regency]
                    val subLocality = listAddress?.get(0)?.subLocality
                    val locality = listAddress?.get(0)?.locality
                    val resultLocation = "$subLocality.$locality"
                    Log.i("LocPref", "getKnownLastLocation: $resultOfCity")
                    if (resultOfCity != null) {
                        val listCity = listOf(resultOfCity[1], resultLocation)
                        lastKnownLocation.postValue(listCity)
                    } else {
                        val listCity = listOf("Bogor", resultLocation)
                        lastKnownLocation.postValue(listCity)
                    }
                }
            } else {
                Toast.makeText(context, "Sorry, something wrong...", Toast.LENGTH_SHORT).show()
            }
        }

        fusedLocation.lastLocation.addOnFailureListener {
            Log.e("SharedViewModel", "getKnownLastLocation: " + it.localizedMessage)
        }
        return lastKnownLocation
    }
}
