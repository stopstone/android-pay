package com.stopstone.payapp.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.stopstone.payapp.R
import com.stopstone.payapp.databinding.FragmentTransferConfirmDialogBinding
import com.stopstone.payapp.ui.extensions.applyBoldStyle
import com.stopstone.payapp.ui.extensions.convertThreeDigitComma

class TransferConfirmDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentTransferConfirmDialogBinding? = null
    private val binding get() = _binding!!
    private val args: TransferConfirmDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransferConfirmDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        binding.tvTransferMessage.applyBoldStyle(
            R.string.format_transfer_info,
            args.account.holderName,
            args.amount.convertThreeDigitComma()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}