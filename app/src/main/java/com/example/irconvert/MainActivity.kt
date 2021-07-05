package com.example.irconvert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnCon = findViewById<Button>(R.id.btnCalculate)

        val naturalYear = 12.0
        var yearSalary = 0.0
        val INNSPorcent = 0.07
        var inssYear = 0.0
        var inssMonth = 0.0
        var salaryWithoutinss = 0.0
        var excedente = 0.0
        var impuestoBase = 0.0
        var IRyear = 0.0
        var IRmonth = 0.0

        btnCon.setOnClickListener{

            var textviss = findViewById<TextView>(R.id.textvInnss)
            var textvir = findViewById<TextView>(R.id.textvIR)
            var textvTotal = findViewById<TextView>(R.id.textvTotalSalary)

            var etxt = findViewById<EditText>(R.id.etxtSalary)

            var salary = etxt.text.toString().toInt()
            yearSalary = (salary*naturalYear)

            inssYear = (yearSalary*INNSPorcent)
            inssMonth = (inssYear/naturalYear)
            textviss.text = ("Deducción Inss: ${inssMonth.toString()}")

            fun SalaryRange(): Double {
                salaryWithoutinss = yearSalary - inssYear
                return salaryWithoutinss
            }

            fun ImpuestoBase() {
                impuestoBase = if (SalaryRange() in 0.0..200000.00){
                    0.00
                }
                else if (SalaryRange() in 200000.01..350000.00){
                    15000.00
                }
                else if (SalaryRange() in 350000.01..500000.00){
                    45000.00
                }
                else{
                    82500.00
                }
            }
            ImpuestoBase()

            fun AplicandoIR() {
                when(SalaryRange()){
                    in 0.0..100000.00 -> {
                        println("Usted no debe pagar Impuesto sobre renta (IR)")
                    }
                    in 100000.01..200000.00 -> {
                        excedente = SalaryRange() - 100000
                        IRyear = excedente * 0.15
                        IRmonth = IRyear / naturalYear
                        textvir.text = ("Resultado IR al mes: $IRyear ($IRmonth al mes)")
                    }
                    in 200000.01..350000.00 -> {
                        excedente = SalaryRange() - 200000
                        IRyear = (excedente * 0.2) + impuestoBase
                        IRmonth = IRyear / naturalYear
                        textvir.text = ("Resultado IR al mes: $IRyear ($IRmonth al mes)")
                    }
                    in 350000.01..500000.00 -> {
                        excedente = SalaryRange() - 350000
                        IRyear = (excedente * 0.25) + impuestoBase
                        IRmonth = IRyear / naturalYear
                        textvir.text = ("Resultado IR al mes: $IRyear ($IRmonth al mes)")
                    }
                    else -> {
                        excedente = SalaryRange() - 500000
                        IRyear = (excedente * 0.3) + impuestoBase
                        IRmonth = IRyear / naturalYear
                        textvir.text = ("Resultado IR al mes: $IRyear ($IRmonth al mes)")
                    }
                }
            }
            AplicandoIR()
        }
    }
}