package ru.denisspirin.homeworkmovieslist.db

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun flagToBool(flag: String?): Boolean {
        return flag?.equals("Y") ?: false
    }

    @TypeConverter
    fun boolToFlag(bool: Boolean?): String? {
        return if (bool == null || bool) "Y" else null
    }
}