package com.stopstone.payapp.data

data class Account(
    val profileResourceId: Int,
    val holderName: String,
    val bankName: String,
    val accountNumber: String
)