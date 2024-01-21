package com.stopstone.payapp.ui.transferaccount

import com.stopstone.payapp.data.Account

interface TransferAccountItemClickListener {
    fun onTransferAccountClick(account: Account) {
    }
}