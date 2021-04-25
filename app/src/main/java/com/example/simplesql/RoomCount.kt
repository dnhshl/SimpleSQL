package com.example.simplesql

class RoomCount(val mRoom: String, val mCount: Int) {
    override fun toString(): String {
        return "$mRoom : $mCount x"
    }
}