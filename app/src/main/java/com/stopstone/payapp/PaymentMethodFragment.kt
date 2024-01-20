package com.stopstone.payapp

import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.stopstone.payapp.databinding.FragmentPaymentMethodBinding

private const val PASSWORD_FORMAT_LENGTH = 2
private const val DURATION_FORMAT_LENGTH = 4
private const val DURATION_FORMAT_DELIMITER = "/"

class PaymentMethodFragment : Fragment() {

    private var _binding: FragmentPaymentMethodBinding? = null
    private val biding get() = _binding!!

    private var isValidCardNumber = false
    private var isValidDate = false
    private var isValidPassword = false
    private var isValidCardName = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentMethodBinding.inflate(inflater, container, false)
        return biding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        setTextInput()
        setSubmitPaymentInfo()
    }

    private fun setTextInput() {
        isValidMaxLength()
        biding.etInputCardNumber.doAfterTextChanged {
            val cardNumber = it?.toString() ?: ""
            isValidCardNumber = isValidText(cardNumber)
            updateButtonEnableState()
        }
        biding.etInputValidDate.doAfterTextChanged {
            val validDate = it?.toString()
            if (validDate.isNullOrBlank() || validDate.length < DURATION_FORMAT_LENGTH) {
                isValidDate = false
                updateButtonEnableState()
                return@doAfterTextChanged
            }
            if (isValidDuration(validDate)) {
                applyDurationFormat(validDate)
                isValidDate = true
            } else if (!isValidDurationFormat(validDate)) {
                resetDurationFormat(validDate)
                isValidDate = false
            }
            updateButtonEnableState()
        }
        biding.etInputPassword.doAfterTextChanged {
            val password = it?.toString() ?: ""
            isValidPassword = isValidPassword(password)
            updateButtonEnableState()
        }
        biding.etInputCardName.doAfterTextChanged {
            val cardName = it?.toString() ?: ""
            isValidCardName = isValidText(cardName)
            updateButtonEnableState()
        }
    }


    private fun isValidText(text: String): Boolean {
        return text.isNotBlank()
    }

    private fun isValidMaxLength() {
        biding.etInputValidDate.filters =
            arrayOf(InputFilter.LengthFilter(DURATION_FORMAT_LENGTH + 1))
        biding.etInputPassword.filters = arrayOf(InputFilter.LengthFilter(PASSWORD_FORMAT_LENGTH))
    }

    private fun isValidDuration(text: String): Boolean {
        return text.length == DURATION_FORMAT_LENGTH && !text.contains(DURATION_FORMAT_DELIMITER)
    }

    private fun isValidDurationFormat(text: String): Boolean {
        return text.length == DURATION_FORMAT_LENGTH + 1 && text.contains(DURATION_FORMAT_DELIMITER)
    }

    private fun applyDurationFormat(text: String) {
        val monthCharacterSize = 2
        val month = text.substring(0, monthCharacterSize)
        val year = text.substring(monthCharacterSize, DURATION_FORMAT_LENGTH)
        biding.etInputValidDate.setText("$month$DURATION_FORMAT_DELIMITER$year")
        biding.etInputValidDate.setSelection(DURATION_FORMAT_LENGTH + 1)
    }

    private fun resetDurationFormat(text: String) {
        biding.etInputValidDate.setText(text.replace(DURATION_FORMAT_DELIMITER, ""))
        biding.etInputValidDate.setSelection(text.lastIndex)
    }

    private fun isValidPassword(text: String): Boolean {
        return text.length == PASSWORD_FORMAT_LENGTH
    }

    private fun updateButtonEnableState() {
        biding.btnSubmitCardInfo.isEnabled =
            isValidCardNumber && isValidDate && isValidPassword && isValidCardName
    }

    private fun setSubmitPaymentInfo() {
        biding.btnSubmitCardInfo.setOnClickListener {
            val action = PaymentMethodFragmentDirections.actionPaymentMethodToPaymentRegistration()
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}