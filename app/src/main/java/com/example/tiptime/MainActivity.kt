package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        binding.calculateTip.setOnClickListener {
            calculateTip()
        }
        setContentView(binding.root)
    }
    private fun calculateTip(){
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.resultTip.text = ""
            return
        }
        val tipPercent = when (binding.tipOption.checkedRadioButtonId) {
            R.id.hai_muoi -> 0.2
            R.id.muoi_lam -> 0.15
            else -> 0.1
        }
        var resultCal = cost * (1 - tipPercent)
        if (binding.roundUpTip.isChecked) {
            resultCal = kotlin.math.ceil(resultCal)
        }
        val formattedTip = NumberFormat.getCurrencyInstance(Locale("vi", "VN")).format(resultCal)
        binding.resultTip.text = getString(R.string.result_tip, formattedTip)
    }
}