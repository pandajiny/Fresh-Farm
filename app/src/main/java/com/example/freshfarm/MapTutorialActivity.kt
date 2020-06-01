package com.example.freshfarm

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.Nullable
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.CircleOverlay
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay

class MapTutorialActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var naverMap: NaverMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_tutorial)
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)

        findViewById<FloatingActionButton>(R.id.mapFAB).setOnClickListener {
            Action()
        }

    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        naverMap.setOnMapClickListener { pointF, latLng -> Log.w("location", latLng.toString()) }

//        naverMap.setOnMapClickListener { pointF, latLng -> Log.w("location", pointF.toString()) }


//        add marker
        val cityhallMarker = Marker()
        cityhallMarker.position = LatLng(37.5670135, 126.9783740)
        cityhallMarker.map = naverMap

        val infoWindow = InfoWindow()
        infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(this) {
            override fun getText(infoWindow: InfoWindow): CharSequence {
                return "정보 창 내용"
            }
        }

        infoWindow.open(cityhallMarker)

        val circle = CircleOverlay()
        circle.center = LatLng(37.5666102, 126.9783881)
        circle.radius = 500.0
        circle.map = naverMap
        circle.color = Color.TRANSPARENT
        circle.outlineColor = Color.BLACK
        circle.outlineWidth = 10
        val listener: Overlay.OnClickListener = object : Overlay.OnClickListener {
            override fun onClick(p0: Overlay): Boolean {
                Action()
                return true
            }
        }
        circle.onClickListener = listener

    }

    private fun Action() {
        if (naverMap != null) {
            val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.5666102, 126.9783881))
            naverMap.moveCamera(cameraUpdate)
        }
    }
}
