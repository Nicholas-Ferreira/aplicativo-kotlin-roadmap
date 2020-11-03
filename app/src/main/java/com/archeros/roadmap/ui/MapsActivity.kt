package com.archeros.roadmap.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.archeros.roadmap.DebugActivity
import com.archeros.roadmap.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.toolbar.*

class MapsActivity : DebugActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        setSupportActionBar(toolbar_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.nav_eventos)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_in_night));

        val impacta = LatLng(-23.52, -46.64)
        mMap.addMarker(MarkerOptions()
            .position(impacta)
            .title("Feira de Carreiras")
            .snippet("Impacta"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(impacta, 18f))

        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

    }
}