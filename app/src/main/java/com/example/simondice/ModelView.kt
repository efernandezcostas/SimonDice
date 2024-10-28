package com.example.simondice

import android.util.Log


class ModelView {

    var numeroRandom = 0

    fun crearRandom(){
        numeroRandom = (1..4).random()
        actualizarNumero()
        Log.d("Numero", numeroRandom.toString())
    }

    fun actualizarNumero(){
        Datos.numero = numeroRandom
        Log.d("Numero", Datos.numero.toString())
    }
}