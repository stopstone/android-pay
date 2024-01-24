package com.stopstone.payapp.data

import com.stopstone.payapp.R

object Storage {
    val accountList: List<Account> = getDummyData()
    var paymentMethod: PaymentMethod? = null
        private set
    private fun getDummyData(): List<Account> {
        return listOf(
            Account(R.drawable.img_1, "정지석", "농협은행", "356 0608 8388 33"),
            Account(R.drawable.img_2, "김민지", "기업은행", "010 9409 6432"),
            Account(R.drawable.img_3, "팜하니", "신한은행", "356 0608 8388 33"),
            Account(R.drawable.img_4, "김현수", "우리은행", "356 0608 8388 33"),
            Account(R.drawable.img_5, "아이비", "카카오뱅크", "356 0608 8388 33"),
            Account(R.drawable.img_6, "한가영", "우체국", "356 0608 8388 33"),
            Account(R.drawable.img_7, "신짱구", "기업은행", "010 9409 6432"),
            Account(R.drawable.img_8, "김진원", "농협은행", "356 0608 8388 33"),
            Account(R.drawable.img_9, "김철수", "국민은행", "010 9409 6432"),
        )
    }

    fun savePaymentMethod(method: PaymentMethod): Boolean {
        paymentMethod = method.copy(cardBalance = 7500000)
        // TODO Network
        return true
    }

    fun postTransfer(account:Account, amount: Long): Boolean {
        paymentMethod?.run {
            val newBalance = cardBalance - amount
            paymentMethod = copy(cardBalance = newBalance)

        }
        return true
    }
}
