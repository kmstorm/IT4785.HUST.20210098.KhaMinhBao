package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var textResult: TextView

    private var op: Int = 0
    private var op1: Double = 0.0
    private var op2: Double = 0.0
    private var currentInput: String = ""
    private var isOp1: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.display)

        findViewById<Button>(R.id.button_0).setOnClickListener(this)
        findViewById<Button>(R.id.button_1).setOnClickListener(this)
        findViewById<Button>(R.id.button_2).setOnClickListener(this)
        findViewById<Button>(R.id.button_3).setOnClickListener(this)
        findViewById<Button>(R.id.button_4).setOnClickListener(this)
        findViewById<Button>(R.id.button_5).setOnClickListener(this)
        findViewById<Button>(R.id.button_6).setOnClickListener(this)
        findViewById<Button>(R.id.button_7).setOnClickListener(this)
        findViewById<Button>(R.id.button_8).setOnClickListener(this)
        findViewById<Button>(R.id.button_9).setOnClickListener(this)
        findViewById<Button>(R.id.button_add).setOnClickListener(this)
        findViewById<Button>(R.id.button_minus).setOnClickListener(this)
        findViewById<Button>(R.id.button_multiply).setOnClickListener(this)
        findViewById<Button>(R.id.button_divide).setOnClickListener(this)
        findViewById<Button>(R.id.button_equal).setOnClickListener(this)
        findViewById<Button>(R.id.button_ce).setOnClickListener(this)
        findViewById<Button>(R.id.button_c).setOnClickListener(this)
        findViewById<Button>(R.id.button_pn).setOnClickListener(this)
        findViewById<Button>(R.id.button_dot).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val id = view?.id
        when (id) {
            R.id.button_ce -> clearEntry()
            R.id.button_c -> clearAll()
            R.id.button_0 -> addDigit(0)
            R.id.button_1 -> addDigit(1)
            R.id.button_2 -> addDigit(2)
            R.id.button_3 -> addDigit(3)
            R.id.button_4 -> addDigit(4)
            R.id.button_5 -> addDigit(5)
            R.id.button_6 -> addDigit(6)
            R.id.button_7 -> addDigit(7)
            R.id.button_8 -> addDigit(8)
            R.id.button_9 -> addDigit(9)
            R.id.button_add -> setOperation(1)
            R.id.button_minus -> setOperation(2)
            R.id.button_multiply -> setOperation(3)
            R.id.button_divide -> setOperation(4)
            R.id.button_pn -> toggleSign()
            R.id.button_dot -> addDecimalPoint()
            R.id.button_equal -> calculate()
        }
    }

    private fun addDigit(digit: Int) {
        currentInput += digit.toString()
        updateDisplay(currentInput)
    }

    private fun addDecimalPoint() {
        if (!currentInput.contains(".")) {
            currentInput += "."
            updateDisplay(currentInput)
        }
    }

    private fun setOperation(operation: Int) {
        if (currentInput.isNotEmpty()) {
            if (isOp1) {
                op1 = currentInput.toDouble()
                currentInput = ""
                isOp1 = false
            } else {
                calculateIntermediate()
            }
        }
        op = operation
    }

    private fun calculateIntermediate() {
        if (currentInput.isNotEmpty()) {
            val op2 = currentInput.toDouble()
            var result = 0.0
            when (op) {
                1 -> result = op1 + op2
                2 -> result = op1 - op2
                3 -> result = op1 * op2
                4 -> if (op2 != 0.0) result = op1 / op2
            }
            updateDisplay(formatNumber(result))
            op1 = result
            currentInput = ""
        }
    }

    private fun calculate() {
        if (currentInput.isNotEmpty()) {
            op2 = currentInput.toDouble()
            var result = 0.0
            when (op) {
                1 -> result = op1 + op2
                2 -> result = op1 - op2
                3 -> result = op1 * op2
                4 -> if (op2 != 0.0) result = op1 / op2
            }
            updateDisplay(formatNumber(result))
            resetAfterCalculation(result)
        }
    }

    private fun resetAfterCalculation(result: Double) {
        op1 = result
        op2 = 0.0
        currentInput = ""
        isOp1 = true
        op = 0
    }

    private fun clearEntry() {
        currentInput = ""
        textResult.text = "0"
    }

    private fun clearAll() {
        currentInput = ""
        op1 = 0.0
        op2 = 0.0
        op = 0
        textResult.text = "0"
        isOp1 = true
    }

    private fun toggleSign() {
        if (currentInput.isNotEmpty()) {
            val value = currentInput.toDouble()
            currentInput = formatNumber(-value)
            updateDisplay(currentInput)
        }
    }

    private fun formatNumber(value: Double): String {
        return if (value == value.toInt().toDouble()) {
            value.toInt().toString()
        } else {
            value.toString()
        }
    }

    private fun updateDisplay(value: String) {
        textResult.text = value

        val decimalIndex = value.indexOf('.')
        val digitsAfterDecimal = if (decimalIndex != -1) value.length - decimalIndex - 1 else 0

        val baseSize = 100f
        val newSize = if (digitsAfterDecimal > 3) baseSize - (digitsAfterDecimal - 3) * 10 else baseSize
        textResult.textSize = newSize.coerceAtLeast(50f)
    }
}