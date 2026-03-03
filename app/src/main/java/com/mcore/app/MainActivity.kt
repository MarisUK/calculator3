package com.mcore.app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupInputValidation(inputNumber1)
        setupInputValidation(inputNumber2)

        buttonAdd.setOnClickListener {
            calculate('+')
        }

        buttonSubtract.setOnClickListener {
            calculate('-')
        }

        buttonMultiply.setOnClickListener {
            calculate('*')
        }

        buttonDivide.setOnClickListener {
            calculate('/')
        }
    }

    private fun calculate(operator: Char) {
        val num1 = inputNumber1.text.toString().toDoubleOrNull()
        val num2 = inputNumber2.text.toString().toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Lūdzu, ievadiet derīgus skaitļus", Toast.LENGTH_SHORT).show()
            return
        }

        if (operator == '/' && num2 == 0.0) {
            promptForValidInput("Nevar dalīt ar nulli. Lūdzu, ievadiet derīgu skaitli otrajam skaitlim.")
            return
        }

        val result = when (operator) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            '*' -> num1 * num2
            '/' -> num1 / num2
            else -> null
        }

        textViewResult.text = result?.toString() ?: "Aprēķināšanas kļūda"
    }

    private fun setupInputValidation(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // No operation
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No operation
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    if (s.toString().isNotEmpty()) {
                        s.toString().toDouble()
                        editText.error = null
                    } else {
                        editText.error = "Please enter a number"
                    }
                } catch (e: NumberFormatException) {
                    editText.error = "Nederīgs skaitlis"
                }
            }
        })
    }

    private fun promptForValidInput(message: String) {
        inputNumber2.error = message
        textViewResult.text = ""
    }
}
