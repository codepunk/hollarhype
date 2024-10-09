package com.codepunk.hollarhype.data.local.typeconverter

import androidx.room.TypeConverter
import com.codepunk.hollarhype.util.intl.Region

class RegionTypeConverter {

    @TypeConverter
    fun regionToString(input: Region?): String =
        input?.regionCode ?: ""

    @TypeConverter
    fun stringToRegion(input: String?): Region =
        input?.let { Region.of(it) } ?: Region.getDefault()

}