package com.stopstone.payapp.ui.home

import com.stopstone.payapp.data.Transfer

interface TransferItemClickListener {
    fun transferOnClick(transfer: Transfer)
}