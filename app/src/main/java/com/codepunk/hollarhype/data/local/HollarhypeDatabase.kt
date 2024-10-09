package com.codepunk.hollarhype.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codepunk.hollarhype.data.local.dao.ActivityDao
import com.codepunk.hollarhype.data.local.dao.GroupDao
import com.codepunk.hollarhype.data.local.dao.MessageDao
import com.codepunk.hollarhype.data.local.dao.RunDao
import com.codepunk.hollarhype.data.local.dao.SponsorDao
import com.codepunk.hollarhype.data.local.dao.UserDao
import com.codepunk.hollarhype.data.local.entity.LocalActivity
import com.codepunk.hollarhype.data.local.entity.LocalGroup
import com.codepunk.hollarhype.data.local.entity.LocalMessage
import com.codepunk.hollarhype.data.local.entity.LocalRun
import com.codepunk.hollarhype.data.local.entity.LocalSponsor
import com.codepunk.hollarhype.data.local.entity.LocalUser
import com.codepunk.hollarhype.data.local.typeconverter.BigDecimalTypeConverter
import com.codepunk.hollarhype.data.local.typeconverter.InstantTypeConverter
import com.codepunk.hollarhype.data.local.typeconverter.LocalDateTimeTypeConverter
import com.codepunk.hollarhype.data.local.typeconverter.LocalDateTypeConverter
import com.codepunk.hollarhype.data.local.typeconverter.RegionTypeConverter

@Database(
    version = 1,
    entities = [
        LocalActivity::class,
        LocalGroup::class,
        LocalMessage::class,
        LocalRun::class,
        LocalSponsor::class,
        LocalUser::class
    ]
)
@TypeConverters(
    value = [
        BigDecimalTypeConverter::class,
        InstantTypeConverter::class,
        LocalDateTypeConverter::class,
        LocalDateTimeTypeConverter::class,
        RegionTypeConverter::class
    ]
)
abstract class HollarhypeDatabase: RoomDatabase() {

    // region Methods

    abstract fun activityDao(): ActivityDao

    abstract fun groupDao(): GroupDao

    abstract fun messageDao(): MessageDao

    abstract fun runDao(): RunDao

    abstract fun sponsorDao(): SponsorDao

    abstract fun userDao(): UserDao

    // endregion Methods

}