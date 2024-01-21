package com.stopstone.payapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Account(
    val profileResourceId: Int,
    val holderName: String,
    val bankName: String,
    val accountNumber: String
) : Parcelable