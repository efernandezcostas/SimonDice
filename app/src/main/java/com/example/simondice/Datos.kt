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

enum class Estados (var boton_start: Boolean, var boton_color: Boolean, var texto: String){
    INICIO(boton_start = true, boton_color = false, texto = "Bienvenido a Simón Dice"),
    GENERANDO(boton_start = false, boton_color = false, texto = "Simón Dice"),
    RESPONDIENDO(boton_start = false, boton_color = true, texto = "Tú Dices"),
}

enum class EstadosAuxiliares (var texto: String){
    AUX1("aux1"),
    AUX2("aux2"),
    AUX3("aux3"),
}