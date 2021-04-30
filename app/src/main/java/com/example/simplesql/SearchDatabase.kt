package com.example.simplesql

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import splitties.toast.toast


class SearchDatabase : AppCompatActivity() {
    private val TAG = "SearchDatabaseActivity"

    private val btnSearch: Button by lazy{ findViewById(R.id.button_search) }
    private val listViewData: ListView by lazy{ findViewById(R.id.listView_Data) }
    private val eInput: EditText by lazy{ findViewById(R.id.edit_inputSearch) }
    private val radioGroup: RadioGroup by lazy{ findViewById(R.id.radioGroup) }
    private var listData = ArrayList<Weather>()

    private lateinit var mDb: AppDatabase
    private lateinit var mWeatherDao: WeatherDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_database)

        mDb = AppDatabase.getAppDatabase(context = this)!!
        mWeatherDao = mDb.getWeatherDao()

        btnSearch.setOnClickListener {
            getInput()
        }
    }

    private fun getInput() {
        val id = radioGroup.checkedRadioButtonId
        val input = eInput.text.toString()
        if (validate(input, id)) {
            when (id) {
                R.id.radioButton_room -> handleRoom(input)
                R.id.radioButton_temperature -> handleTemperature(input.toInt())
                R.id.radioButton_date -> handleDate(input)
                R.id.radioButton_hottest -> handleHottest()
                R.id.radioButton_delete -> delete(input.toInt())
                else -> getAll()
            }
        } else {
            toast(R.string.fields_empty)
        }
    }


    private fun getAll() {
        listData.clear()
        try {
            listData = mWeatherDao.loadDbList() as ArrayList<Weather>
            if (listData.isNotEmpty()) {
                Log.i(TAG, listData.toString())
                updateList()
            } else {
                toast(R.string.empty_db)
                toast(R.string.spelling_remind)
            }
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "$e")
        }
    }

    private fun handleRoom(room: String) {
        listData.clear()
        try {
            listData = mWeatherDao.findRoom(room) as ArrayList<Weather>
            if (listData.isNotEmpty()) {
                Log.i(TAG, listData.toString())
                updateList()
            } else {
                toast(R.string.empty_db)
                toast(R.string.spelling_remind)
            }
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "$e")
        }
    }

    private fun handleDate(date: String) {
        listData.clear()
        try {
            listData = mWeatherDao.findDate(date) as ArrayList<Weather>
            if (listData.isNotEmpty()) {
                Log.i(TAG, listData.toString())
                updateList()
            } else {
                toast(R.string.empty_db)
                toast(R.string.spelling_remind)
            }
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "$e")
        }
    }

    private fun handleTemperature(temp: Int) {
        listData.clear()
        try {
            listData = mWeatherDao.findTemp(temp) as ArrayList<Weather>
            if (listData.isNotEmpty()) {
                Log.i(TAG, listData.toString())
                updateList()
            } else {
                toast(R.string.empty_db)
                toast(R.string.spelling_remind)
            }
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "$e")
        }
    }

    private fun handleHottest() {
        listData.clear()
        try {
            listData = mWeatherDao.findHottest() as ArrayList<Weather>
            if (listData.isNotEmpty()) {
                Log.i(TAG, listData.toString())
                updateList()
            } else {
                toast(R.string.empty_db)
                toast(R.string.spelling_remind)
            }
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "$e")
        }
    }

    private fun delete(number: Int) {
        try {
            mWeatherDao.deleteSelected(number)
            toast(R.string.delete_success)
            listData = mWeatherDao.loadDbList() as ArrayList<Weather>
            updateList()
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "$e")
        }
    }

    private fun updateList() {
        val adapter: ArrayAdapter<Weather> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listData)
        listViewData.adapter = adapter
    }

    private fun validate(input: String, id: Int): Boolean {
        var valid = true
        if (input.isEmpty() && id != -1) {
            valid = false
        }
        if (id == R.id.radioButton_temperature || id == R.id.radioButton_hottest || id == R.id.radioButton_delete) {
            val number = input.toIntOrNull()
            if (number == null) {
                valid = false
            }
        }
        if (id == R.id.radioButton_hottest) {
            valid = true
        }
        return valid
    }

}