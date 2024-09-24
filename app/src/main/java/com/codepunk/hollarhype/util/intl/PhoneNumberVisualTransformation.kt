package com.codepunk.hollarhype.util.intl

import android.telephony.PhoneNumberUtils
import android.text.Selection
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.util.Locale

/**
 * A phone visual transformation that works for any country code.
 */

// See https://medium.com/google-developer-experts/hands-on-jetpack-compose-visualtransformation-to-create-a-phone-number-formatter-99b0347fc4f6
// (With 1 minor bug dealing with incrementing specialCharsCount AFTER updating transformedToOriginal)

class PhoneNumberVisualTransformation(
    countryCode: String = Locale.getDefault().country
) : VisualTransformation {

    private val phoneNumberUtil = PhoneNumberUtil.getInstance()
    private val phoneNumberFormatter = phoneNumberUtil.getAsYouTypeFormatter(countryCode)
    private val originalToTransformed = mutableListOf<Int>()
    private val transformedToOriginal = mutableListOf<Int>()

    override fun filter(text: AnnotatedString): TransformedText {
        val formatted = reformat(text, Selection.getSelectionEnd(text))

        return TransformedText(
            AnnotatedString(formatted.orEmpty()),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int) =
                    originalToTransformed[offset.coerceIn(originalToTransformed.indices)]
                override fun transformedToOriginal(offset: Int) =
                    transformedToOriginal[offset.coerceIn(transformedToOriginal.indices)]
            }
        )
    }

    private fun reformat(s: CharSequence, cursor: Int): String? {
        phoneNumberFormatter.clear()
        originalToTransformed.clear()
        transformedToOriginal.clear()

        val curIndex = cursor - 1
        var formatted: String? = null
        var lastNonSeparator = 0.toChar()
        var hasCursor = false

        s.forEachIndexed { index, char ->
            if (PhoneNumberUtils.isNonSeparator(char)) {
                if (lastNonSeparator.code != 0) {
                    formatted = getFormattedNumber(lastNonSeparator, hasCursor)
                    hasCursor = false
                }
                lastNonSeparator = char
            }
            if (index == curIndex) {
                hasCursor = true
            }
        }

        if (lastNonSeparator.code != 0) {
            formatted = getFormattedNumber(lastNonSeparator, hasCursor)
        }
        var specialCharsCount = 0
        formatted?.forEachIndexed { index, char ->
            if (!PhoneNumberUtils.isNonSeparator(char)) {
                transformedToOriginal.add(index - specialCharsCount++)
            } else {
                originalToTransformed.add(index)
                transformedToOriginal.add(index - specialCharsCount)
            }
        }
        originalToTransformed.add(originalToTransformed.maxOrNull()?.plus(1) ?: 0)
        transformedToOriginal.add(transformedToOriginal.maxOrNull()?.plus(1) ?: 0)

        return formatted
    }

    private fun getFormattedNumber(lastNonSeparator: Char, hasCursor: Boolean): String? {
        return if (hasCursor) {
            phoneNumberFormatter.inputDigitAndRememberPosition(lastNonSeparator)
        } else {
            phoneNumberFormatter.inputDigit(lastNonSeparator)
        }
    }
}
