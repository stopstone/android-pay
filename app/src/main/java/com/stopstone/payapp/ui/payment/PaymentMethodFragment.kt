package com.stopstone.payapp.ui.payment

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.stopstone.payapp.data.PaymentMethod
import com.stopstone.payapp.databinding.FragmentPaymentMethodBinding

private const val PASSWORD_FORMAT_LENGTH = 2
private const val DURATION_FORMAT_LENGTH = 4
private const val DURATION_FORMAT_DELIMITER = "/"
class PaymentMethodFragment : Fragment() {

    private var _binding: FragmentPaymentMethodBinding? = null
    private val biding get() = _binding!!

    private var cardNumber = ""
    private var validMonth = ""
    private var validYear = ""
    private var cardName = ""

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
            cardNumber = it?.toString() ?: ""
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
            cardName = it?.toString() ?: ""
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
        validMonth = text.substring(0, monthCharacterSize)
        validYear = text.substring(monthCharacterSize, DURATION_FORMAT_LENGTH)
        biding.etInputValidDate.setText("$validMonth$DURATION_FORMAT_DELIMITER$validYear")
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
            val paymentMethod = PaymentMethod(
                cardNumber = cardNumber,
                validMonth = validMonth,
                validYear = validYear,
                cardName = cardName,
            )
            val action =
                PaymentMethodFragmentDirections.actionPaymentMethodToPaymentRegistration(
                    paymentMethod
                )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}