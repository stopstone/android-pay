package com.stopstone.payapp.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stopstone.payapp.databinding.FragmentTransferBinding
import com.stopstone.payapp.ui.extensions.applyNumberFormat

class TransferFragment : Fragment() {
    private var _binding: FragmentTransferBinding? = null
    private val binding get() = _binding!!
    private val args: TransferFragmentArgs by navArgs()
    private var amount = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        setAccountInfo()
        setAmountInputArea()
        with(binding) {
            val keypadButtons = listOf<Button>(
                btnNumber1, btnNumber2, btnNumber3,
                btnNumber4, btnNumber5, btnNumber6,
                btnNumber7, btnNumber8, btnNumber9,
                btnNumber00, btnNumber0
            )
            keypadButtons.forEach { btnNumber ->
                setNumberClickListener(btnNumber)
            }
            setDeleteAction(btnNumberDelete)
            btnSubmitTransfer.setOnClickListener {
                val action = TransferFragmentDirections.actionTransferToTransferConfirmDialog(
                    args.account, amount
                )
                findNavController().navigate(action)
            }

            toolbarTransfer.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setAmountInputArea() {
        binding.tvTransferAmount.doAfterTextChanged {
            val isValid = it?.toString().isNullOrBlank() || it?.toString().equals("0")
            binding.btnSubmitTransfer.isEnabled = !isValid
            if (isValid) {
                binding.tvLabelTransferAmount.visibility = VISIBLE
                binding.tvTransferAmount.visibility = GONE
            }
            if (!isValid) {
                binding.tvTransferAmount.visibility = VISIBLE
                binding.tvLabelTransferAmount.visibility = GONE
            }
        }

    }

    private fun setNumberClickListener(view: Button) {
        view.setOnClickListener {
            val pre = binding.tvTransferAmount.getNumberValue()
            amount = "$pre${view.text}".toLong()
            binding.tvTransferAmount.applyNumberFormat(amount)
        }
    }

    private fun setDeleteAction(view: AppCompatImageButton) {
        view.setOnClickListener {
            val current = binding.tvTransferAmount.getNumberValue()
            if (current.isNotEmpty()) {
                val new = current.substring(0, current.lastIndex)
                if (new.isNotEmpty()) {
                    binding.tvTransferAmount.applyNumberFormat(new.toLong())
                }
                if (new.isEmpty()) {
                    binding.tvTransferAmount.text = ""
                }
            }
        }
    }

    private fun TextView.getNumberValue(): String {
        return text.replace(Regex(","), "")
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