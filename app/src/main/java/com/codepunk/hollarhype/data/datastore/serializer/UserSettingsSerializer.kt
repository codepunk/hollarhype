package com.codepunk.hollarhype.data.datastore.serializer

import android.util.Log
import androidx.datastore.core.Serializer
import com.codepunk.hollarhype.data.datastore.entity.UserSettings
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object UserSettingsSerializer : Serializer<UserSettings> {
    override val defaultValue: UserSettings
        get() = UserSettings()

    override suspend fun readFrom(input: InputStream): UserSettings = try {
        Json.decodeFromString(
            deserializer = UserSettings.serializer(),
            string = input.readBytes().decodeToString()
        )
    } catch (e: SerializationException) {
        Log.e(javaClass.simpleName, e.message, e)
        defaultValue
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(t: UserSettings, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = UserSettings.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}
