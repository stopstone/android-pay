package com.stopstone.payapp.ui.transferdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stopstone.payapp.R
import com.stopstone.payapp.databinding.FragmentTransferDetailBinding
import com.stopstone.payapp.ui.extensions.convertThreeDigitComma

class TransferDetailFragment: Fragment() {
    private var _binding: FragmentTransferDetailBinding? = null
    private val binding get() = _binding!!
    private val args: TransferDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        val transfer = args.transfer
        val account = transfer.account
        val holderName = transfer.account.holderName
        with(binding) {
            tvDetailHolderName.text = holderName
            tvDetailAmount.text = getString(R.string.format_amount_unit, transfer.amount.convertThreeDigitComma())
            viewTransferReceiver.setValue(holderName)
            viewTransferType.setValue(getString(R.string.label_withdraw))
            viewTransferDate.setValue(transfer.sendDate)
            viewTransferBalance.setValue(getString(R.string.format_amount_unit, transfer.balance.convertThreeDigitComma()))
            viewTransferAccount.setValue(getString(R.string.format_transfer_account, account.bankName, account.accountNumber))

            binding.toolbarTransferDetail.setNavigationOnClickListener{
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}