package com.example.simplelistapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI components initialization
        val inputNumberField = findViewById<EditText>(R.id.etNumber)
        val radioEven = findViewById<RadioButton>(R.id.rbEven)
        val radioOdd = findViewById<RadioButton>(R.id.rbOdd)
        val radioSquare = findViewById<RadioButton>(R.id.rbSquare)
        val displayButton = findViewById<Button>(R.id.btnShow)
        val resultListView = findViewById<ListView>(R.id.listView)
        val errorText = findViewById<TextView>(R.id.tvError)

        displayButton.setOnClickListener {
            val numberInput = inputNumberField.text.toString().toIntOrNull()

            // Validate the input
            if (numberInput == null || numberInput <= 0) {
                errorText.text = "Enter a valid positive integer"
                return@setOnClickListener
            }

            // Get result based on selected option
            val numberList = when {
                radioEven.isChecked -> createEvenList(numberInput)
                radioOdd.isChecked -> createOddList(numberInput)
                radioSquare.isChecked -> createSquareList(numberInput)
                else -> {
                    errorText.text = "Please select a type of number."
                    return@setOnClickListener
                }
            }

            // Update UI
            errorText.text = ""
            resultListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numberList)
        }
    }

    private fun createEvenList(maxNumber: Int): List<Int> {
        return List(maxNumber / 2) { it * 2 + 2 }
    }

    private fun createOddList(maxNumber: Int): List<Int> {
        return List((maxNumber + 1) / 2) { it * 2 + 1 }
    }

    private fun createSquareList(maxNumber: Int): List<Int> {
        return (1..maxNumber).mapNotNull { num ->
            val root = sqrt(num.toDouble()).toInt()
            if (root * root == num) num else null
        }
    }
}
