package com.stopstone.payapp

data class PaymentMethod(
    val cardNumber: String,
    val validMonth: String,
    val validYear: String,
    val cardName: String,
)
