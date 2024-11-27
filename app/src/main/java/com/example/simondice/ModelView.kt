package com.example.simondice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Clase que contiene la lógica de la aplicación
 */
class ModelView: ViewModel() {
    /**
     * LiveData que contiene el estado de la aplicación
     */
    val estadoLiveData: MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)

    /**
     * Función que genera un número aleatorio y cambia el estado de la aplicación a GENERANDO
     */
    fun generarRandom(){
        estadoLiveData.value = Estados.GENERANDO
        Datos.numero = (1..4).random()
        addASecuencia(colorSegunRandom())

        Log.d("Propio", "Secuencia máquina: ${Datos.secuenciaMaquina}")
    }

    /**
     * Función que devuelve un color según el número aleatorio generado
     */
    private fun colorSegunRandom(): Colores{
        return when(Datos.numero){
            1 -> Colores.AZUL
            2 -> Colores.ROJO
            3 -> Colores.VERDE
            4 -> Colores.AMARILLO
            else -> throw IllegalArgumentException("Número no posible: ${Datos.numero}")
        }
    }

    /**
     * Función que añade un color a la secuencia de la máquina
     */
    private fun addASecuencia(color: Colores){
        Datos.secuenciaMaquina.add(color)
    }

    /**
     * Función que añade un color a la secuencia del usuario
     */
    fun addASecuenciaUser(color: Colores, setTextoPartida: (String) -> Unit){
        Datos.secuenciaUsuario.add(color)
        val botonAcertado = comprobarIndices()
        if (botonAcertado) setTextoPartida("Siguiente ronda")
        else setTextoPartida("Nueva partida")
    }

    /**
     * Función que comprueba si los índices de las secuencias de la máquina y del usuario son iguales,
     * en caso de no serlo, se pierde la partida
     */
    private fun comprobarIndices(): Boolean {
        Log.d("Propio", "${Datos.secuenciaMaquina} - ${Datos.secuenciaUsuario}")

        estadosAuxiliares()

        if (Datos.secuenciaMaquina[Datos.secuenciaUsuario.size-1] == Datos.secuenciaUsuario.last()) {
            if (Datos.secuenciaMaquina.size == Datos.secuenciaUsuario.size){
                estadoLiveData.value = Estados.INICIO.apply { texto = "¡Has superado la ronda ${Datos.ronda}!" }
                Datos.ronda += 1
                Datos.secuenciaUsuario.clear()
            }
        } else {
            estadoLiveData.value = Estados.INICIO.apply { texto = "¡Has perdido en la ronda ${Datos.ronda}!" }
            Datos.ronda = 1
            Datos.secuenciaMaquina.clear()
            Datos.secuenciaUsuario.clear()
            return false
        }
        return true
    }

    /**
     * Función que contiene los estados auxiliares de la aplicación
     */
    private fun estadosAuxiliares(){
        viewModelScope.launch {
            var estadoAuxiliar = EstadosAuxiliares.AUX1

            Log.d("propio", "estado (coroutine): $estadoAuxiliar")
            estadoAuxiliar = EstadosAuxiliares.AUX2
            delay(1500)
            Log.d("propio", "estado (coroutine): $estadoAuxiliar")
            delay(1500)
            estadoAuxiliar = EstadosAuxiliares.AUX3
            Log.d("propio", "estado (coroutine): $estadoAuxiliar")
            delay(1500)
        }
    }
}