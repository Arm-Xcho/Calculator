package com.example.calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.calculator.databinding.FragmentTextViewsBinding
import kotlin.properties.Delegates

class TextViewsFragment : Fragment(), CalculatorButtonsClicked {

    private lateinit var binding: FragmentTextViewsBinding
    var historyString = ""
    private var isOperator = false
    private var hasOperator = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTextViewsBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Chi pahum Infon rotate aneluc
        if (savedInstanceState != null) {
            binding.tvOutput.text = savedInstanceState.getString("key")
        }

//        savedInstanceState?.takeIf { it.containsKey(KEY_OUTPUT_TEXT_VALUE) }?.let {
//            binding.tvOutput.text = it.getString(KEY_OUTPUT_TEXT_VALUE)
//        }
//        savedInstanceState?.takeIf { it.containsKey(KEY_INPUT_TEXT_VALUE) }?.let {
//            binding.tvInput.text = it.getString(KEY_INPUT_TEXT_VALUE)
//        }

    }

    override fun btnClicked(value: String) {
        when (value) {
            in "0".."9" -> numberButtonClicked(value)
            "/", "X", "+", "-", "%" -> operatorButtonClicked(value)
            "=" -> equalsButtonClicked()
            "ac" -> acButtonClicked()
            "bs" -> backButtonClicked()
            "history" -> goHistoryActivity()
        }
    }

    private fun numberButtonClicked(number: String) {
        with(binding) {
            if (isOperator) {
                tvInput.append(" ")
            }
            isOperator = false

            tvInput.text.toString()

            tvInput.append(number)
            tvOutput.text = calculate()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun operatorButtonClicked(operator: String) {
        if (binding.tvInput.text.toString().isEmpty()) {
            return
        }

        when {
            isOperator -> {
                val text = binding.tvInput.text
                binding.tvInput.text = text.dropLast(1).toString() + operator
            }

            else -> {
                binding.tvInput.append(" $operator")
            }

        }
        val ssb = SpannableStringBuilder(binding.tvInput.text)

        binding.tvInput.text = ssb
        isOperator = true
        hasOperator = true
    }

    private fun equalsButtonClicked(): String {
        val expressionTexts = binding.tvInput.text.split(" ")
        if (binding.tvInput.text.isEmpty() || expressionTexts.size == 1) {
            return ""
        }
        val resultText = calculate()

        binding.tvOutput.text = ""
        binding.tvInput.text = resultText

        isOperator = false
        hasOperator = false

        historyString += "${expressionTexts[0]}  ${expressionTexts[1]}  ${expressionTexts[2]} " +
                "= $resultText \n "

        return resultText
    }


    private fun calculate(): String {
        val expressionTexts = binding.tvInput.text.split(" ")

        if (hasOperator.not() || expressionTexts.size != 3) {
            return ""
        } else if (expressionTexts[0].isNumber().not() || expressionTexts[2].isNumber().not()) {
            return ""
        }
        val exp1 = expressionTexts[0].toInt()
        val exp2 = expressionTexts[2].toInt()

        return when (expressionTexts[1]) {
            "+" -> (exp1 + exp2).toString()
            "-" -> (exp1 - exp2).toString()
            "X" -> (exp1 * exp2).toString()
            "%" -> ((exp1 / 100) * exp2).toString()
            "/" -> (exp1 / exp2).toString()
            else -> ""
        }
    }

    private fun acButtonClicked() {
        binding.tvInput.text = ""
        binding.tvOutput.text = ""
        isOperator = false
        hasOperator = false
    }

    private fun backButtonClicked() {
        binding.tvInput.text = binding.tvInput.text.dropLast(1)
        isOperator = false
        hasOperator = false
    }

    private fun String.isNumber(): Boolean {
        return try {
            this.toBigInteger()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun goHistoryActivity() {
        val intent = Intent(
            getActivity(),
            HistoryActivity::class.java,
        )
        intent.putExtra(EXTRA_1, historyString)
        getActivity()?.startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("key", binding.tvOutput.text.toString())
//        outState.putString(KEY_INPUT_TEXT_VALUE, binding.tvInput.text.toString())
    }


    companion object {
        @JvmStatic
        fun newInstance() = TextViewsFragment()
        const val EXTRA_1 = "EXTRA_1"
        const val KEY_INPUT_TEXT_VALUE = "KEY_INPUT_TEXT_VALUE"
        const val KEY_OUTPUT_TEXT_VALUE = "KEY_OUTPUT_TEXT_VALUE"

    }
}