package com.example.simondice

import androidx.lifecycle.ViewModel


class ModelView: ViewModel() {

    /**
     * Genera un número aleatorio y añade el color correspondiente a la secuencia de la máquina
     */
    fun nuevaRonda(){
        Datos.numero = (1..4).random()
        when (Datos.numero) {
            1 -> Datos.secuenciaMaquina.add(Colores.AZUL)
            2 -> Datos.secuenciaMaquina.add(Colores.ROJO)
            3 -> Datos.secuenciaMaquina.add(Colores.VERDE)
            4 -> Datos.secuenciaMaquina.add(Colores.AMARILLO)
        }
        Datos.secuenciaUsuario.clear()
    }


    /**
     * Compara el color seleccionado por el usuario con el color de la secuencia de la máquina
     * @param color Color seleccionado por el usuario
     * @param setSecuenciaCompletaLocal Función que indica si la secuencia del usuario está completa
     * @return true si el color seleccionado por el usuario es correcto, false en caso contrario
     */
    fun respuestaUsuario(color: Colores, setSecuenciaCompletaLocal: (Boolean) -> Unit): Boolean {
        Datos.secuenciaUsuario.add(color)
        if (Datos.secuenciaUsuario.size == Datos.secuenciaMaquina.size){
            setSecuenciaCompletaLocal(true)
        }
        return Datos.secuenciaUsuario.last() == Datos.secuenciaMaquina[Datos.secuenciaUsuario.size - 1]
    }

    /**
     * Limpia la secuencia de la máquina
     */
    fun nuevaPartida(){
        Datos.secuenciaMaquina.clear()
    }
}