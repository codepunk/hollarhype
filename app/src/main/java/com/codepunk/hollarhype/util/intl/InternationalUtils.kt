package com.codepunk.hollarhype.util.intl


import android.os.Parcelable
import com.google.i18n.phonenumbers.PhoneNumberUtil
import kotlinx.parcelize.Parcelize
import java.util.Locale as Locale

private const val UNICODE_REGIONAL_INDICATOR_SYMBOL_LETTER_A: Int = 0x1F1E6
private const val UNICODE_CAPITAL_A: Int = 0x41

fun getSupportedRegions(): List<Region> =
    PhoneNumberUtil.getInstance().run {
        supportedRegions.map { regionCode ->
            Region.of(regionCode = regionCode, phoneNumberUtil = this)
        }.sorted()
    }

fun getCountryNameForRegion(regionCode: String): String =
    Locale("", regionCode).displayCountry

fun getFlagEmojiForRegion(regionCode: String): String = regionCode.takeIf {
    it.length == 2 && it.all { char -> char.isLetter() }
}.orEmpty().map { char ->
    char.code - UNICODE_CAPITAL_A + UNICODE_REGIONAL_INDICATOR_SYMBOL_LETTER_A
}.joinToString(separator = "") { codePoint ->
    String(Character.toChars(codePoint))
}

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
            phoneNumberUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()
        ): Region = Region(
            regionCode = regionCode,
            countryCode = phoneNumberUtil.getCountryCodeForRegion(regionCode),
            countryName = getCountryNameForRegion(regionCode),
            flagEmoji = getFlagEmojiForRegion(regionCode)
        )
    }
}
