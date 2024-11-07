package com.example.simondice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ModelView: ViewModel() {
    val estadoLiveData: MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)

    fun generarRandom(){
        estadoLiveData.value = Estados.GENERANDO
        Datos.numero = (1..4).random()
        addASecuencia(colorSegunRandom())

        Log.d("Propio", "Secuencia máquina: ${Datos.secuenciaMaquina}")
    }

    private fun colorSegunRandom(): Colores{
        return when(Datos.numero){
            1 -> Colores.AZUL
            2 -> Colores.ROJO
            3 -> Colores.VERDE
            4 -> Colores.AMARILLO
            else -> throw IllegalArgumentException("Número no posible: ${Datos.numero}")
        }
    }

    private fun addASecuencia(color: Colores){
        Datos.secuenciaMaquina.add(color)
    }

    fun addASecuenciaUser(color: Colores){
        Datos.secuenciaUsuario.add(color)
        comprobarIndices()
    }

    private fun comprobarIndices() {
        Log.d("Propio", "${Datos.secuenciaMaquina} - ${Datos.secuenciaUsuario}")

        if (Datos.secuenciaMaquina[Datos.secuenciaUsuario.size-1] == Datos.secuenciaUsuario.last()) {
            if (Datos.secuenciaMaquina.size == Datos.secuenciaUsuario.size){
                estadoLiveData.value = Estados.INICIO.apply { texto = "¡Has superado la ronda ${Datos.ronda}" }
                Datos.ronda += 1
                Datos.secuenciaUsuario.clear()
            }
        } else {
            estadoLiveData.value = Estados.INICIO.apply { texto = "¡Has perdido en la ronda ${Datos.ronda}" }
            Datos.ronda = 1
            Datos.secuenciaMaquina.clear()
            Datos.secuenciaUsuario.clear()
        }
    }
}