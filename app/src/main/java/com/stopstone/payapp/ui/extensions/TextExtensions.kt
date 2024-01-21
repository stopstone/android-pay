package com.stopstone.payapp.ui.extensions

import android.graphics.Typeface
import android.icu.text.DecimalFormat
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.widget.TextView

fun TextView.applyNumberFormat(amount: Long) {
    text = amount.convertThreeDigitComma()
}

fun Number.convertThreeDigitComma(): String {
    val decimalFormat = DecimalFormat("#,###")
    return decimalFormat.format(this)
}

fun TextView.applyBoldStyle(resId: Int, vararg content: String) {
    val message = context.getString(resId, *content)
    text = SpannableStringBuilder(message).apply {
        content.forEach {
            val start = message.indexOf(it)
            setSpan(
                StyleSpan(Typeface.BOLD),
                start,
                start + it.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}