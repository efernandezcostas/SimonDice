package com.example.simondice

import androidx.lifecycle.ViewModel


class ModelView: ViewModel() {

    /**
     * Al empezar una ronda:
     * Genera un número aleatorio y guarda el numero en Datos.numero. Actualiza los datos para una nueva ronda.
     */
    fun generarRandom(){
        Datos.numero = (1..4).random()

        val color = colorSegunRandom()
        addASecuencia(color)

        Datos.secuenciaUsuario.clear()
        Datos.ronda++
    }

    /**
     * Añade el color a la secuencia de la maquina
     */
    private fun colorSegunRandom(): Colores{
        var color = Colores.AZUL
        when (Datos.numero) {
            1 -> color = Colores.AZUL
            2 -> color = Colores.ROJO
            3 -> color = Colores.VERDE
            4 -> color = Colores.AMARILLO
        }
        return color
    }

    /**
     * Comprueba si el color pulsado por el usuario es el mismo que el de la secuencia de la máquina
     * @param color Color según el número random
     */
    private fun addASecuencia(color: Colores){
        Datos.secuenciaMaquina.add(color)
    }

    /**
     * Comprueba si el color pulsado por el usuario es el mismo que el de la secuencia de la máquina
     * @param color Color pulsado por el usuario
     * @param setSecuenciaCompletaLocal Función que cambia el valor de secuenciaCompleta
     * @return true si son iguales, false en caso contrario
     */
    fun addASecuenciaUser(color: Colores, setSecuenciaCompletaLocal: (Boolean) -> Unit): Boolean {
        Datos.secuenciaUsuario.add(color)
        if (Datos.secuenciaUsuario.size == Datos.secuenciaMaquina.size){
            setSecuenciaCompletaLocal(true)
        }
        return compararIndices()
    }

    /**
     * Compara el último color de la secuencia de la máquina con el último color de la secuencia del usuario
     * @return true si son iguales, false en caso contrario
     */
    private fun compararIndices(): Boolean {
        return Datos.secuenciaUsuario.last() == Datos.secuenciaMaquina[Datos.secuenciaUsuario.size - 1]
    }

    /**
     * Limpia la secuencia de la máquina
     */
    fun nuevaPartida(){
        Datos.secuenciaMaquina.clear()
        Datos.ronda = 0
    }
}