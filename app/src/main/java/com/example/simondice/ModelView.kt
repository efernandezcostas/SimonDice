package com.example.simondice

import android.util.Log
import androidx.compose.runtime.MutableIntState
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

    fun comprobarSecuencia(numeroSecuencia: Int, secuenciaColores: MutableList<Colores>, secuenciaUsuario: MutableList<Colores>) {
        if (secuenciaColores[numeroSecuencia-1] == secuenciaUsuario[numeroSecuencia-1]){
            Log.d("Secuencia","Ganaste "+secuenciaColores[numeroSecuencia-1]+" "+secuenciaUsuario[numeroSecuencia-1]+" "+(numeroSecuencia-1))
        } else {
            Log.d("Secuencia","Perdiste "+secuenciaColores[numeroSecuencia-1]+" "+secuenciaUsuario[numeroSecuencia-1]+" "+(numeroSecuencia-1))
        }
    }
}