package com.stopstone.payapp.data

import com.stopstone.payapp.R

object Storage {
    val accountList: List<Account> = getDummyData()
    var paymentMethod: PaymentMethod? = null
        private set
    private fun getDummyData(): List<Account> {
        return listOf(
            Account(R.drawable.img_1, "정지석", "농협은행", "356 0608 8388 33"),
            Account(R.drawable.img_2, "정지석", "기업은행", "010 9409 6432"),
            Account(R.drawable.img_3, "정지석", "농협은행", "356 0608 8388 33"),
            Account(R.drawable.img_4, "정지석", "농협은행", "356 0608 8388 33"),
            Account(R.drawable.img_5, "정지석", "농협은행", "356 0608 8388 33"),
            Account(R.drawable.img_6, "정지석", "농협은행", "356 0608 8388 33"),
            Account(R.drawable.img_7, "정지석", "기업은행", "010 9409 6432"),
            Account(R.drawable.img_8, "정지석", "농협은행", "356 0608 8388 33"),
            Account(R.drawable.img_9, "정지석", "기업은행", "010 9409 6432"),
        )
    }

    fun savePaymentMethod(method: PaymentMethod): Boolean {
        paymentMethod = method.copy(cardBalance = 7500000)
        // TODO Network
        return true
    }
}
