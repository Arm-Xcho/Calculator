package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var tvInput: TextView
    lateinit var tvOutput: TextView

    private var isOperator = false
    private var hasOperator = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tv_input)
        tvOutput = findViewById(R.id.tv_output)

        val btn0: Button = findViewById(R.id.btn_0)
        btn0.setOnClickListener {
            numberButtonClicked("0")
        }
        val btn1: Button = findViewById(R.id.btn_1)
        btn1.setOnClickListener {
            numberButtonClicked("1")
        }
        val btn2: Button = findViewById(R.id.btn_2)
        btn2.setOnClickListener {
            numberButtonClicked("2")
        }
        val btn3: Button = findViewById(R.id.btn_3)
        btn3.setOnClickListener {
            numberButtonClicked("3")
        }
        val btn4: Button = findViewById(R.id.btn_4)
        btn4.setOnClickListener {
            numberButtonClicked("4")
        }
        val btn5: Button = findViewById(R.id.btn_5)
        btn5.setOnClickListener {
            numberButtonClicked("5")
        }
        val btn6: Button = findViewById(R.id.btn_6)
        btn6.setOnClickListener {
            numberButtonClicked("6")
        }
        val btn7: Button = findViewById(R.id.btn_7)
        btn7.setOnClickListener {
            numberButtonClicked("7")
        }
        val btn8: Button = findViewById(R.id.btn_8)
        btn8.setOnClickListener {
            numberButtonClicked("8")
        }
        val btn9: Button = findViewById(R.id.btn_9)
        btn9.setOnClickListener {
            numberButtonClicked("9")
        }


        val btnDivide: Button = findViewById(R.id.btn_divide)
        btnDivide.setOnClickListener {
            operatorButtonClicked("/")
        }
        val btnMultiplication: Button = findViewById(R.id.btn_multiplication)
        btnMultiplication.setOnClickListener {
            operatorButtonClicked("X")
        }
        val btnMinus: Button = findViewById(R.id.btn_minus)
        btnMinus.setOnClickListener {
            operatorButtonClicked("-")
        }
        val btnPlus: Button = findViewById(R.id.btn_plus)
        btnPlus.setOnClickListener {
            operatorButtonClicked("+")
        }
        val btnPercent: Button = findViewById(R.id.btn_percent)
        btnPercent.setOnClickListener {
            operatorButtonClicked("%")
        }

        val btnEquals: Button = findViewById(R.id.btn_equals)
        btnEquals.setOnClickListener {
            equalsButtonClicked(it)
        }

        val btnAC: Button = findViewById(R.id.btn_C)
        btnAC.setOnClickListener {
            acButtonClicked(it)
        }
        val btnBackSpace: Button = findViewById(R.id.btn_backSpace)
        btnBackSpace.setOnClickListener {
            backButtonClicked(it)
        }


        val btnPlusMinus: Button = findViewById(R.id.btn_plusMinus)
        val btnPoint: Button = findViewById(R.id.btn_point)
    }


    private fun numberButtonClicked(number: String) {
        if (isOperator) {
            tvInput.append(" ")
        }
        isOperator = false

        tvInput.text.toString()

        tvInput.append(number)
        tvOutput.text = calculate()
    }


    @SuppressLint("SetTextI18n")
    private fun operatorButtonClicked(operator: String) {
        if (tvInput.text.toString().isEmpty()) {
            return
        }

        when {
            isOperator -> {
                val text = tvInput.text
                tvInput.text = text.dropLast(1).toString() + operator
            }

            else -> {
                tvInput.append(" $operator")
            }

        }
        val ssb = SpannableStringBuilder(tvInput.text)

        tvInput.text = ssb
        isOperator = true
        hasOperator = true
    }

    private fun equalsButtonClicked(v: View) {
        val expressionTexts = tvInput.text.split(" ")
        if (tvInput.text.isEmpty() || expressionTexts.size == 1) {
            return
        }
        val resultText = calculate()

        tvOutput.text = ""
        tvInput.text = resultText

        isOperator = false
        hasOperator = false

    }


    private fun calculate(): String {
        val expressionTexts = tvInput.text.split(" ")

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

    private fun acButtonClicked(v: View) {
        tvInput.text = ""
        tvOutput.text = ""
        isOperator = false
        hasOperator = false
    }

    private fun backButtonClicked(v: View) {
        tvInput.text = tvInput.text.dropLast(1)
        isOperator = false
        hasOperator = false
    }


}

fun String.isNumber(): Boolean {
    return try {
        this.toBigInteger()
        true
    } catch (e: NumberFormatException) {
        false
    }
}