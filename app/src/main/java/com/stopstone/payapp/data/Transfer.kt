package com.stopstone.payapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transfer(
    val account: Account,
    val amount: Long,
    val balance: Long,
    val sendDate: String,
) : Parcelable