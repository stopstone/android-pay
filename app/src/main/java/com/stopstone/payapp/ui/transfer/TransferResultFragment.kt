package com.stopstone.payapp.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stopstone.payapp.R
import com.stopstone.payapp.data.Storage
import com.stopstone.payapp.databinding.FragmentTransferResultBinding
import com.stopstone.payapp.ui.extensions.applyBoldStyle
import com.stopstone.payapp.ui.extensions.convertThreeDigitComma

class TransferResultFragment : Fragment() {
    private var _binding: FragmentTransferResultBinding? = null
    private val binding get() = _binding!!
    private val args: TransferResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        with(binding) {
            if (Storage.postTransfer(args.account, args.amount)) {
                groupSuccessResult.visibility = VISIBLE
                groupFailureResult.visibility = GONE
                tvTransferResult.applyBoldStyle(
                    R.string.format_confirm_info,
                    args.account.holderName,
                    args.amount.convertThreeDigitComma()
                )
                btnTransferSuccess.setOnClickListener {
                    val action = TransferResultFragmentDirections.actionTransferResultToHome()
                    findNavController().navigate(action)
                }
            } else {
                groupFailureResult.visibility = VISIBLE
                groupSuccessResult.visibility = GONE
                btnTransferResultFailure.setOnClickListener {
                    findNavController().navigateUp()
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}