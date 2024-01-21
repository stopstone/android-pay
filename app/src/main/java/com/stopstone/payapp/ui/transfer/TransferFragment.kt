package com.stopstone.payapp.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.stopstone.payapp.databinding.FragmentTransferBinding

class TransferFragment : Fragment() {
    private var _binding: FragmentTransferBinding? = null
    private val binding get() = _binding!!
    private val args: TransferFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAccountInfo()
    }

    private fun setAccountInfo() {
        val account = args.account
        with(binding.layoutTransferAccount) {
            ivAccountImage.setImageResource(account.profileResourceId)
            tvAccountName.text = account.holderName
            tvAccountBankName.text = account.bankName
            tvAccountNumber.text = account.accountNumber
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}