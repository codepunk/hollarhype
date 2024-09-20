package com.codepunk.hollarhype.util

import android.util.Log
import androidx.compose.ui.util.fastJoinToString
import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.util.Locale

object PhoneUtil {

    private const val UNICODE_A: Int = 0x1F1E6

    private val unicodeMap = ('A'..'Z').mapIndexed { index, c ->
        c to String(Character.toChars(UNICODE_A + index))
    }.toMap()

    private val phoneNumberUtil by lazy {
        PhoneNumberUtil.getInstance()
    }

    val supportedRegions by lazy {
        val defaultLocaleRegionCode = Locale.getDefault().country
        phoneNumberUtil.supportedRegions.map {
            Region(it)
        }.sortedWith(
            compareBy(
                { if ( it.regionCode == defaultLocaleRegionCode) 0 else 1 },
                { it.countryName }
            )
        )
    }

    fun initialize() {
        // Force supportedRegions to initialize
        supportedRegions
    }

    fun getFlagEmoji(regionCode: String): String = with(regionCode.uppercase()) {
        when {
            length != 2 -> ""
            any { !it.isLetter() } -> ""
            else -> map {
                unicodeMap.getOrDefault(it, "")
            }.fastJoinToString(separator = "") {
                it
            }
        }
    }

    data class Region(
        val regionCode: String,
        val countryCode: Int = phoneNumberUtil.getCountryCodeForRegion(regionCode),
        val countryName: String = Locale("", regionCode).displayCountry,
        val flagEmoji: String = getFlagEmoji(regionCode)
    )

}