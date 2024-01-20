package com.stopstone.payapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PaymentMethod(
    val cardNumber: String,
    val validMonth: String,
    val validYear: String,
    val cardName: String,
    var cardBalance: Long = 0
) : Parcelable
