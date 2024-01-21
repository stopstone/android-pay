package com.stopstone.payapp.ui.transferaccount

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.payapp.data.Account
import com.stopstone.payapp.databinding.ItemTransferAccountBinding

class TransferAccountListAdapter(
    private val items: List<Account>,
    private val listener: TransferAccountItemClickListener
) : RecyclerView.Adapter<TransferAccountItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransferAccountItemViewHolder {
        return TransferAccountItemViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: TransferAccountItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class TransferAccountItemViewHolder(
    private val binding: ItemTransferAccountBinding,
    private val listener: TransferAccountItemClickListener
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(account: Account) {
        itemView.setOnClickListener {
            listener.onTransferAccountClick(account)
        }
        with(binding) {
            ivAccountImage.setImageResource(account.profileResourceId)
            tvAccountName.text = account.holderName
            tvAccountBankName.text = account.bankName
            tvAccountNumber.text = account.accountNumber
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            listener: TransferAccountItemClickListener
        ): TransferAccountItemViewHolder {
            return TransferAccountItemViewHolder(
                ItemTransferAccountBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), listener
            )
        }
    }
}