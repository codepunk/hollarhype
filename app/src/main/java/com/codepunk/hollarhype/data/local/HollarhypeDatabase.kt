package com.codepunk.hollarhype.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codepunk.hollarhype.data.local.dao.UserDao
import com.codepunk.hollarhype.data.local.entity.LocalAuthToken
import com.codepunk.hollarhype.data.local.entity.LocalUser
import com.codepunk.hollarhype.data.local.typeconverter.BigDecimalTypeConverter
import com.codepunk.hollarhype.data.local.typeconverter.InstantTypeConverter
import com.codepunk.hollarhype.data.local.typeconverter.LocalDateTypeConverter

@Database(
    version = 1,
    entities = [
        LocalAuthToken::class,
        LocalUser::class
    ]
)
@TypeConverters(
    value = [
        BigDecimalTypeConverter::class,
        InstantTypeConverter::class,
        LocalDateTypeConverter::class
    ]
)
abstract class HollarhypeDatabase: RoomDatabase() {

    // region Methods

    abstract fun userDao(): UserDao

    // endregion Methods

}