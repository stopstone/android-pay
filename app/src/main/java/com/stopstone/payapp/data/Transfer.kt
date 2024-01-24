package com.stopstone.payapp.data

data class Transfer(
    val account: Account,
    val amount: Long,
    val balance: Long,
    val sendDate: String,
)