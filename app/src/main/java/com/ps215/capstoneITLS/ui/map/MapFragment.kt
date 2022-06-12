package com.ps215.capstoneITLS.ui.map

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ps215.capstoneITLS.R
import com.ps215.capstoneITLS.data.model.TrafficList
import com.ps215.capstoneITLS.databinding.FragmentMapBinding
import com.ps215.capstoneITLS.ui.ViewModelFactory
import com.ps215.capstoneITLS.ui.trafficdetail.TrafficDetailActivity


class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null

    private val binding get() = _binding!!

    private lateinit var mapViewModel: MapViewModel

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isIndoorLevelPickerEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
        googleMap.uiSettings.isMapToolbarEnabled = true

        setupViewModel(googleMap)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMapBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel(googleMap: GoogleMap) {
        val factory = ViewModelFactory.getInstance(requireContext())
        mapViewModel = ViewModelProvider(this, factory)[MapViewModel::class.java]
        mapViewModel.setTraffic()
        getTraffic(googleMap)
    }

    private fun getTraffic(googleMap: GoogleMap) {
        mapViewModel.getTraffic().observe(viewLifecycleOwner) {
            for (i in 0 until it.size){
                addMarker(it[i], googleMap)
            }

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it[0].latitude.toDouble(), it[0].longitude.toDouble()), 15f))
        }
    }

    private fun addMarker(data: TrafficList, googleMap: GoogleMap){
        googleMap.addMarker(MarkerOptions()
            .position(LatLng(data.latitude.toDouble(), data.longitude.toDouble()))
            .title(data.name)
            .snippet(data.address))!!

        googleMap.setOnInfoWindowClickListener{
            showSelectedItem(data)
            Toast.makeText(activity, data.name + " Selected" , Toast.LENGTH_SHORT).show()
        }
    }



    private fun showSelectedItem(data: TrafficList) {
        val intent = Intent (activity, TrafficDetailActivity::class.java)
        intent.putExtra(TrafficDetailActivity.EXTRA_NAME, data.name)
        intent.putExtra(TrafficDetailActivity.EXTRA_ID, data._id)
        intent.putExtra(TrafficDetailActivity.EXTRA_ADDRESS, data.address)
        activity?.startActivity(intent)
        Toast.makeText(activity, data.name + " Selected" , Toast.LENGTH_SHORT).show()
    }
}