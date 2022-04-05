package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.roundToInt

/*
Below 18.5	Underweight
18.5 – 24.9	Normal
25.0 – 29.9	Overweight
30.0 and above	Obese
*/

class MainActivity : AppCompatActivity() {
    private lateinit var edtWeight: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btn: Button
    private lateinit var ans: TextView
    private lateinit var summary: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()

        btn.setOnClickListener {
            if (edtWeight.text.isNullOrBlank() || edtHeight.text.isNullOrBlank()) {
                Toast.makeText(this@MainActivity, "Please Enter All Fields", Toast.LENGTH_LONG)
                    .show()
            } else {
                val weight = edtWeight.text.toString().toDouble()
                val height = edtHeight.text.toString().toDouble()

                val ansFromFormula = calculateBMI(weight, height)
                ans.text = ansFromFormula.toString()
                val getStatus = getStatusFromStatus(ansFromFormula)
                summary.text = "You are ${getStatus}"
            }
        }

    }

    private fun getStatusFromStatus(ansFromFormula: Double): String {
        if (ansFromFormula < 18.5) {
            return "underweight "
        } else if (ansFromFormula >= 18.5 || ansFromFormula <= 24.9) {
            return "normal "
        } else if (ansFromFormula >= 25.0 || ansFromFormula <= 25.9) {
            return "overweight "
        } else {
            return "obese "
        }
    }

    private fun calculateBMI(weight: Double, height: Double): Double {
        val heightInMeters = getValueInMeters(height)
        val ans = weight / Math.pow(heightInMeters, 2.0)
        return String.format("%.2f", ans).toDouble()
    }

    private fun getValueInMeters(height: Double): Double {
        return height / 100
    }

    private fun initialize() {
        edtWeight = findViewById(R.id.edtWeight)
        edtHeight = findViewById(R.id.editTextCm)
        btn = findViewById(R.id.button)
        ans = findViewById(R.id.ans)
        summary = findViewById(R.id.summary)
    }
}