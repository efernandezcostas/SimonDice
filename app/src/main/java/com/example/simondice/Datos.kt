package com.example.simondice

import androidx.compose.ui.graphics.Color

/**
 * Objeto singleton que contiene los datos de la partida
 */
object Datos {
    var numero = 0
    var ronda = 0
    var secuenciaMaquina = mutableListOf<Colores>()
    var secuenciaUsuario = mutableListOf<Colores>()
}

/**
 * Enum de constantes que representan los colores del juego
 */
enum class Colores (val color: Color, val nombre: String){
    AZUL(Color(25, 25, 194, 255), "AZUL"),
    ROJO(Color(199, 37, 37, 255), "ROJO"),
    VERDE(Color(30, 178, 30, 255), "VERDE"),
    AMARILLO(Color(222, 202, 18, 255), "AMARILLO")
}