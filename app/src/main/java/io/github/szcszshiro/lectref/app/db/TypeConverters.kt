package io.github.szcszshiro.lectref.app.db

import androidx.room.TypeConverter
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

class WeekConverter{
    @TypeConverter
    fun toString(week: DayOfWeek) =
        week.toString()

    @TypeConverter
    fun fromString(string: String) =
        DayOfWeek.valueOf(string)
}

class LocalTimeConverter{
    @TypeConverter
    fun toString(localTime: LocalTime) =
        localTime.toString()

    @TypeConverter
    fun fromString(string: String): LocalTime =
        LocalTime.parse(string)?: LocalTime.now()
}

class LocalDateTimeConverter{
    @TypeConverter
    fun toString(localDateTime: LocalDateTime): String =
        localDateTime.toString()

    @TypeConverter
    fun fromString(string: String): LocalDateTime =
        LocalDateTime.parse(string)?: LocalDateTime.now()
}

class StringListConverter{
    @TypeConverter
    fun toString(stringList: List<String>): String {
        if (stringList.isEmpty()) return ""
        var result = ""
        stringList.forEach{
            result = "$result$it&#&"
        }
        return result.slice(0..result.length-3)
    }

    @TypeConverter
    fun fromString(string: String): List<String> =
        string.split("&#&")
}