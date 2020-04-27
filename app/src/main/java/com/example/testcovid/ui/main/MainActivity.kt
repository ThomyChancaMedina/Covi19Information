package com.example.testcovid.ui.main

import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer

import com.example.testcovid.R
import com.example.testcovid.databinding.ActivityMainBinding
import com.example.testcovid.utils.PermissionRequester
//import com.example.testcovid.utils.UiModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import android.Manifest

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lifecycleScope.viewModel(this)
//    private val coarsePermissionRequester = PermissionRequester(
//        this,
//        Manifest.permission.ACCESS_COARSE_LOCATION
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.model.observe(this, Observer(::updateUi))
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        setSupportActionBar(binding.toolbar)


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    private fun updateUi(model: MainViewModel.UiModel) {

        when (model) {
            is MainViewModel.UiModel.Content -> {
                binding.rtConfirmed.text = model.latestImp.confirmed.value.toString()
                binding.rtRecovered.text = model.latestImp.recovered.value.toString()
                binding.rtDeaths.text= model.latestImp.deaths.value.toString()
            }

            MainViewModel.UiModel.RequestLocationPermission -> viewModel.onRequestedCovid()

        }

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        try {
            mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.style_json
                )
            )
        } catch (e: Resources.NotFoundException) {
            e.printStackTrace()
        }
        moveCamera(LatLng(LAT_DEFAULT, LON_DEFAULT))


    }


    private fun moveCamera(latLng: LatLng) {

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 4f))
    }

    companion object {
        private const val LAT_DEFAULT = 30.360227
        private const val LON_DEFAULT = 114.8260094
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        val searchView: SearchView = menu!!.findItem(R.id.search).actionView as SearchView
        return true;
    }


}