package com.codepunk.hollarhype.util.intl

import android.os.Parcelable
import com.google.i18n.phonenumbers.PhoneNumberUtil
import kotlinx.parcelize.Parcelize
import java.util.Locale as Locale

private const val UNICODE_REGIONAL_INDICATOR_SYMBOL_LETTER_A: Int = 0x1F1E6
private const val UNICODE_CAPITAL_A: Int = 0x41
private const val QUESTION_MARK: String = "\u2753"

private fun List<Int>.codePointsToString(): String = joinToString(separator = "") { codePoint ->
    String(Character.toChars(codePoint))
}

fun getSupportedRegions(): List<Region> =
    PhoneNumberUtil.getInstance().run {
        supportedRegions.map { regionCode ->
            Region.of(regionCode = regionCode)
        }.sorted()
    }

fun getCountryNameForRegion(
    regionCode: String,
    unknownCountryName: (String) -> String = { regionCode }
): String = if (PhoneNumberUtil.getInstance().supportedRegions.contains(regionCode)) {
    Locale("", regionCode).displayCountry
} else {
    unknownCountryName(regionCode)
}

fun getFlagEmojiForRegion(
    regionCode: String,
    unknownCountryFlagEmoji: (String) -> String = { QUESTION_MARK }
): String = regionCode.takeIf {
    PhoneNumberUtil.getInstance().supportedRegions.contains(regionCode)
}?.run {
    map { char ->
        char.code - UNICODE_CAPITAL_A + UNICODE_REGIONAL_INDICATOR_SYMBOL_LETTER_A
    }.codePointsToString()
} ?: unknownCountryFlagEmoji(regionCode)

@Parcelize
data class Region(
    val regionCode: String,
    val countryCode: Int,
    val countryName: String,
    val flagEmoji: String
): Comparable<Region>, Parcelable {
    override fun compareTo(other: Region): Int = compareBy<Region>(
        { if (it.regionCode == Locale.getDefault().country) 0 else 1 },
        { it.regionCode }
    ).compare(this, other)

    companion object {
        fun getDefault(): Region = of(Locale.getDefault().country)

        fun of(
            regionCode: String,
            unknownCountryName: (String) -> String = { regionCode },
            unknownCountryFlagEmoji: (String) -> String = { QUESTION_MARK }
        ): Region = Region(
            regionCode = regionCode,
            countryCode = PhoneNumberUtil.getInstance().getCountryCodeForRegion(regionCode),
            countryName = getCountryNameForRegion(regionCode, unknownCountryName),
            flagEmoji = getFlagEmojiForRegion(regionCode, unknownCountryFlagEmoji)
        )
    }
}
