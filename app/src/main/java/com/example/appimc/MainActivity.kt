package com.example.appimc

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var etPeso: EditText
    private lateinit var etEstatura: EditText
    private lateinit var etIMC: EditText
    private lateinit var tvCategoriaIMC: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarComponentes()

        btnCalcular.setOnClickListener{

            calcularIMCClic();
        }

        btnLimpiar.setOnClickListener {

            limpiar()
        }

    }

    private fun inicializarComponentes() {

        btnCalcular = findViewById(R.id.btn_calcular)
        btnLimpiar = findViewById(R.id.btn_limpiar)

        etPeso = findViewById(R.id.et_peso)
        etEstatura = findViewById(R.id.et_estatura)
        etIMC = findViewById(R.id.et_imc)
        tvCategoriaIMC = findViewById(R.id.tv_categoria_imc)

    }

    fun calcularIMCClic(){

        var peso = etPeso.text.toString().toFloat()
        var estatura = etEstatura.text.toString().toFloat()
        var imc =  calcularIMC(peso, estatura)

        etIMC.setText(imc.toString())

       var categoria = calcularCategoriaIMC(imc)

        tvCategoriaIMC.text = categoria
        tvCategoriaIMC.setTextColor(calcularColorCategoriaIMC(categoria))

    }

    fun  limpiar(){

        etPeso.text.clear()
        etEstatura.text.clear()
        etIMC.text.clear()

        etPeso.requestFocus()
    }

    fun calcularIMC(peso:Float,estatura:Float ):Float{

        return peso/(estatura*estatura)
    }

    fun calcularCategoriaIMC(imc:Float):String{


        val categoria = when {
            imc < 18.5 -> "Bajo peso"
            imc in 18.5..24.9 -> "Peso normal"
            imc in 25.0..29.9 -> "Sobrepeso"
            imc in 30.0..34.9 -> "Obesidad tipo I"
            imc in 35.0..39.9 -> "Obesidad tipo II"
            else -> "Obesidad tipo III"
        }

        return categoria
    }


    fun calcularColorCategoriaIMC(categoria:String):Int{


        val resultado = when {
            categoria =="Bajo peso" -> Color.BLUE
            categoria =="Peso normal"-> Color.GREEN
                categoria =="Sobrepeso"-> Color.YELLOW
                categoria =="Obesidad tipo I"-> Color.RED
                categoria =="Obesidad tipo II"-> Color.MAGENTA
            else -> Color.MAGENTA
        }

        return resultado
    }

}