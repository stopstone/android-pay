package com.stopstone.payapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.stopstone.payapp.data.PaymentMethod
import com.stopstone.payapp.data.Storage
import com.stopstone.payapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        val paymentMethod = Storage.paymentMethod

        if (paymentMethod != null) {
            showPaymentMethod(paymentMethod)
        } else {
            setEmptyPaymentMethod()
        }
    }

    private fun showPaymentMethod(paymentMethod: PaymentMethod) {
        with(binding) {
            groupPaymentMethod.visibility = VISIBLE
            groupAddCard.visibility = GONE
            tvPaymentMethodLabel.text = paymentMethod.cardName
            tvPaymentMethodBalance.text = paymentMethod.cardBalance.toString()
        }
    }

    private fun setEmptyPaymentMethod() {
        with(binding) {
            groupAddCard.visibility = VISIBLE
            groupPaymentMethod.visibility = GONE
            viewCardArea.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeToPaymentMethodFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}