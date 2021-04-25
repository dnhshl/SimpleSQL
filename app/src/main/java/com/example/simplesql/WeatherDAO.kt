package com.example.simplesql

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface WeatherDAO {
    @Insert(onConflict = IGNORE )
    fun insert(vararg weathers: Weather?)

    @Delete
    fun delete(weather: Weather)

    @Query("SELECT * FROM TABLE_WEATHER")
    fun loadDbList(): List<Weather>

    @Query("DELETE FROM TABLE_WEATHER WHERE weatherId = :value")
    fun deleteSelected(value: Int)

    @Query("SELECT * FROM TABLE_WEATHER WHERE Room = :value")
    fun findRoom(value: String): List<Weather>

    @Query("SELECT * FROM TABLE_WEATHER WHERE Temperature >= :value")
    fun findTemp(value: Int): List<Weather>

    @Query("SELECT * FROM TABLE_WEATHER WHERE Date = :value")
    fun findDate(value: String): List<Weather>

    @Query("SELECT * FROM TABLE_WEATHER ORDER BY Temperature DESC LIMIT 5")
    fun findHottest(): List<Weather>

    @Query("SELECT COUNT(Room) AS mCount, Room AS mRoom FROM TABLE_WEATHER GROUP BY Room")
    fun getRoomCount(): List<RoomCount>

}