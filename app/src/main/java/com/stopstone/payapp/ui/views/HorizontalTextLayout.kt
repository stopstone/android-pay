package com.stopstone.payapp.ui.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.stopstone.payapp.R
import com.stopstone.payapp.databinding.ViewHorizontalTextInfoBinding

class HorizontalTextLayout(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    private val binding : ViewHorizontalTextInfoBinding

    init {
        binding = ViewHorizontalTextInfoBinding.inflate(LayoutInflater.from(context), this)
        context.obtainStyledAttributes(attrs, R.styleable.HorizontalTextLayout).apply {
            try {
                val labelResId = getResourceId(R.styleable.HorizontalTextLayout_textLabel, 0)
                setLabel(labelResId)
            } catch (e: Exception) {
                Log.d("HorizontalTextLayout", "error : ${e.message}")
            } finally {
                recycle()
            }
        }
    }

    private fun setLabel(labelResId: Int) {
        binding.tvTextLabel.setText(labelResId)
    }

    fun setValue(text: String) {
        binding.tvTextValue.text = text
    }
}