package com.example.liza.au_2018_jvm2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        myMuseumsBtn.setOnClickListener(::launchMyMuseums)
        allMuseumsBtn.setOnClickListener(::launchAllMuseums)
        mapBtn.setOnClickListener(::launchMap)
    }

//    fun launchMyMuseums(v: View) {
//        val intent = Intent(this, MyMuseumsActivity::class.java)
//        startActivity(intent)
//    }

    fun launchAllMuseums(v: View) {
        val intent = Intent(this, AllMuseumsActivity::class.java)
        startActivity(intent)
    }

    fun launchMap(v: View) {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }
}
