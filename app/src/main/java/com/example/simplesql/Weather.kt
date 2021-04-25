package com.example.simplesql

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "TABLE_WEATHER")
data class Weather (
    @NonNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "weatherId") var id: Int? = null,

    @ColumnInfo(name = "Room") var room: String,

    @ColumnInfo(name = "Temperature") var temp: Int,

    @ColumnInfo(name = "Humidity") var hum: Int,

    @ColumnInfo(name = "Date") var date: String
) {
    override fun toString(): String {
        val value = "$id: $room T: $temp °C, H: $hum %, $date"
        return value
    }
}