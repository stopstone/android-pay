package com.stopstone.payapp.ui.transferaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.stopstone.payapp.data.Account
import com.stopstone.payapp.data.Storage
import com.stopstone.payapp.databinding.FragmentTransferAccountBinding

class TransferAccountFragment : Fragment(), TransferAccountItemClickListener {

    private var _binding: FragmentTransferAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTransferAccountList.adapter =
            TransferAccountListAdapter(Storage.accountList, this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTransferAccountClick(account: Account) {
        val action = TransferAccountFragmentDirections.actionTransferAccountToTransfer(account)
        findNavController().navigate(action)
    }
}