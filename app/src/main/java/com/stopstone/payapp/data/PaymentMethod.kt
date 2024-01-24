package com.stopstone.payapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PaymentMethod(
    val cardNumber: String,
    val validMonth: String,
    val validYear: String,
    val cardName: String,
    val cardBalance: Long = 0L
) : Parcelable
