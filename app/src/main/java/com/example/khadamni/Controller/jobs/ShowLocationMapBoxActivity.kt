package com.example.khadamni.Controller.jobs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.khadamni.R
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
class ShowLocationMapBoxActivity : AppCompatActivity() {

    var mapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_location_map_box)



        mapView = findViewById(R.id.mapView)
        mapView?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS)
    }


    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }
}