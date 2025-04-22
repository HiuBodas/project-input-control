package com.example.tugas

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var btnPickDate: Button
    private lateinit var btnPickTime: Button
    private lateinit var btnInputAlert: Button

    private var selectedDate = ""
    private var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPickDate = findViewById(R.id.btnPickDate)
        btnPickTime = findViewById(R.id.btnPickTime)
        btnInputAlert = findViewById(R.id.btnInputAlert)

        btnPickDate.setOnClickListener { showDatePicker() }
        btnPickTime.setOnClickListener { showTimePicker() }
        btnInputAlert.setOnClickListener { showInputAlert() }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()

        val datePicker = DatePickerDialog(this,
            { _, year, month, day ->
                selectedDate = "$day/${month + 1}/$year"
                showAlert("Tanggal Dipilih", selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()

        val timePicker = TimePickerDialog(this,
            { _, hourOfDay, minute ->
                selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                showAlert("Waktu Dipilih", selectedTime)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePicker.show()
    }

    private fun showInputAlert() {
        val inputEditText = EditText(this)
        inputEditText.hint = "Masukkan sesuatu..."

        AlertDialog.Builder(this)
            .setTitle("Input Dialog")
            .setMessage("Silakan masukkan teks:")
            .setView(inputEditText)
            .setPositiveButton("OK") { _, _ ->
                val inputText = inputEditText.text.toString()
                Toast.makeText(this, "Kamu ngetik: $inputText", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun showAlert(title: String, message: String) {
        val alert = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()
        alert.show()
    }
}
