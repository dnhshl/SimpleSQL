package com.example.simplesql

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import splitties.toast.toast

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val etRoom : TextView by lazy{ findViewById(R.id.etRoom) }
    private val etTemp : TextView by lazy{ findViewById(R.id.etTemp) }
    private val etAir : TextView by lazy{ findViewById(R.id.etAir) }
    private val etDate : TextView by lazy{ findViewById(R.id.etDate) }

    private lateinit var mDb: AppDatabase
    private lateinit var mWeatherDao: WeatherDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDb = AppDatabase.getAppDatabase(context = this)!!
        mWeatherDao = mDb.getWeatherDao()

    }

    fun onClickButton(v: View?) {

        val sRaum = etRoom.text.toString()
        val sTemp = etTemp.text.toString()
        val sHum = etAir.text.toString()
        val sDate = etDate.text.toString()

        if (sRaum.isEmpty() || sTemp.isEmpty() || sHum.isEmpty() || sDate.isEmpty()) {
            toast(getString(R.string.eingabefehler))
            Log.d(TAG, getString(R.string.eingabefehler))

        } else {
            toast(getString(R.string.gespeichert))
            val temp = sTemp.toInt()
            val hum = sHum.toInt()
            insert(Weather(room = sRaum, temp = temp, hum = hum, date = sDate))
            Log.i(TAG, "$sRaum $sTemp $sHum $sDate")
        }
    }

    private fun insert(weather: Weather) {
        try {
            mWeatherDao.insert(weather)
            Log.i(TAG, "${weather} successful")
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "$e no success!")
        }
    }
}