package com.example.simondice

import androidx.lifecycle.ViewModel


class ModelView: ViewModel() {

    /**
     * Inicia una nueva ronda
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
     * Añade el color seleccionado por el usuario a la secuencia del usuario
     */
    fun respuestaUsuario(color: Colores, setSecuenciaCompletaLocal: (Boolean) -> Unit): Boolean {
        Datos.secuenciaUsuario.add(color)
        if (Datos.secuenciaUsuario.size == Datos.secuenciaMaquina.size){
            setSecuenciaCompletaLocal(true)
        }
        return Datos.secuenciaUsuario.last() == Datos.secuenciaMaquina[Datos.secuenciaUsuario.size - 1]
    }

    fun nuevaPartida(){
        Datos.secuenciaMaquina.clear()
    }
}