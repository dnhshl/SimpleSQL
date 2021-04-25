package com.example.simplesql

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import splitties.toast.toast

class MainActivity : AppCompatActivity() {

    private val etRoom : TextView by lazy{ findViewById(R.id.etRoom) }
    private val etTemp : TextView by lazy{ findViewById(R.id.etTemp) }
    private val etAir : TextView by lazy{ findViewById(R.id.etAir) }
    private val etDate : TextView by lazy{ findViewById(R.id.etDate) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickButton(v: View?) {

        val sRaum = etRoom.text.toString()
        val sTemp = etTemp.text.toString()
        val sHum = etAir.text.toString()
        val sDate = etDate.text.toString()

        if (sRaum.isEmpty() || sTemp.isEmpty() || sHum.isEmpty() || sDate.isEmpty()) {
            toast(getString(R.string.eingabefehler))
        } else {
            toast(getString(R.string.gespeichert))
        }
    }
}