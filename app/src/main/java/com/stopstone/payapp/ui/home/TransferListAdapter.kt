package com.stopstone.payapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.payapp.R
import com.stopstone.payapp.data.Transfer
import com.stopstone.payapp.databinding.ItemTransferHistoryBinding
import com.stopstone.payapp.ui.extensions.convertThreeDigitComma

class TransferListAdapter(private val items: List<Transfer>) :
    RecyclerView.Adapter<TransferItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferItemViewHolder {
        return TransferItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TransferItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class TransferItemViewHolder(private val binding: ItemTransferHistoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(transfer: Transfer) {
        val account = transfer.account

        with(binding) {
            ivAccountHistoryImage.setImageResource(account.profileResourceId)
            tvTransferHistoryName.text = account.holderName
            tvTransferHistoryAmount.text = itemView.context.getString(
                R.string.format_amount_unit,
                transfer.amount.convertThreeDigitComma()
            )
            tvTransferHistoryBalance.text =
                itemView.context.getString(
                    R.string.format_amount_unit,
                    transfer.balance.convertThreeDigitComma()
                )
            tvTransferHistoryDate.text = transfer.sendDate
        }
    }

    companion object {
        fun from(parent: ViewGroup): TransferItemViewHolder {
            return TransferItemViewHolder(
                ItemTransferHistoryBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

}
