package com.altaie.notes.model.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

class Converters {
    @TypeConverter
    fun listToJsonString(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun dateToLong(value: Date) = value.time

    @TypeConverter
    fun longToDate(value: Long) = Date(value)
}