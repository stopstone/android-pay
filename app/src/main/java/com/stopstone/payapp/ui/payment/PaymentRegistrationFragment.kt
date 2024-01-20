package com.stopstone.payapp.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stopstone.payapp.data.Storage
import com.stopstone.payapp.databinding.FragmentPaymentRegistrationBinding

class PaymentRegistrationFragment : Fragment() {
    private var _binding: FragmentPaymentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val args: PaymentRegistrationFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savePaymentMethod()
    }

    private fun savePaymentMethod() {
        val paymentMethod = args.paymentMethod
        if (Storage.savePaymentMethod(paymentMethod)) {
            setSuccessLayout()
        } else {
            setFailureLayout()
        }
    }

    private fun setSuccessLayout() {
        with(binding) {
            groupPaymentMethodSuccess.visibility = VISIBLE
            groupPaymentMethodFailure.visibility = GONE
            btnExitPaymentMethod.setOnClickListener {
                popPaymentRegistration()
            }
            toolbarPaymentRegistration.setNavigationOnClickListener {
                popPaymentRegistration()
            }
        }
    }

    private fun popPaymentRegistration() {
        val action =
            PaymentRegistrationFragmentDirections.actionPaymentRegistrationToHome()
        findNavController().navigate(action)
    }

    private fun setFailureLayout() {
        with(binding) {
            groupPaymentMethodFailure.visibility = VISIBLE
            groupPaymentMethodSuccess.visibility = GONE
            toolbarPaymentRegistration.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            btnRetryPaymentMethod.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}