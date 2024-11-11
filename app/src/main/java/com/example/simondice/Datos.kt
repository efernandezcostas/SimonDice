package com.example.simondice

import androidx.compose.ui.graphics.Color

object Datos {
    var numero = 0
    var ronda = 1
    var secuenciaMaquina = mutableListOf<Colores>()
    var secuenciaUsuario = mutableListOf<Colores>()
}

enum class Colores (var color: Color, var nombre: String){
    AZUL(Color(25, 25, 194, 255), "AZUL"),
    ROJO(Color(199, 37, 37, 255), "ROJO"),
    VERDE(Color(30, 178, 30, 255), "VERDE"),
    AMARILLO(Color(222, 202, 18, 255), "AMARILLO")

}

enum class Estados (var botonStart: Boolean, var botonColor: Boolean, var texto: String){
    INICIO(botonStart = true, botonColor = false, texto = "Bienvenido a Simón Dice"),
    GENERANDO(botonStart = false, botonColor = false, texto = "Simón Dice"),
    RESPONDIENDO(botonStart = false, botonColor = true, texto = "Tú Dices"),
}

enum class EstadosAuxiliares {
    AUX1,
    AUX2,
    AUX3,
}