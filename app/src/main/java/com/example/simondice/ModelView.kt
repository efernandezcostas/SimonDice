package com.example.simondice

import android.util.Log
import androidx.lifecycle.ViewModel


class ModelView: ViewModel() {

    private var _numeroRandom = 0

    fun crearRandom(){
        _numeroRandom = (1..4).random()
        actualizarNumero()
        Log.d("Numero", _numeroRandom.toString())
    }

    fun actualizarNumero(){
        Datos.numero = _numeroRandom
        Log.d("Numero", Datos.numero.toString())
    }

    fun comprobarSecuencia(secuenciaColores: MutableList<Colores>, secuenciaUsuario: MutableList<Colores>): String{

        var resultado = false



        for (i in 0 until secuenciaColores.size){
            if (secuenciaColores[i] == secuenciaUsuario[i]){
                continue
            } else {
                resultado = false
            }
        }
        return "Ganaste"
    }
}