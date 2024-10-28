// *enum - data class - mutableList - remember

/*
 Rama nueva
 Secuencia 5 numeros random entre 1-4
 Toast muestra la secuencia entera
 El usuario marca la secuencia
 Se comprueba en cada click
    si falla toast game over
    si llega al final toast win
 */

/*
UI(miModelView, @Composable) | ModelView(clase) | Datos(object enum)
click -> start()
        -><- genera un random
        -> almacena
click -> comprueba(botonclick)
        -> getRandom()
                <-
        -><- comprobar
        <- si/no
ganaste/perdiste
 */


package com.example.simondice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.simondice.ui.theme.SimonDiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimonDiceTheme {
                val miModelView = ModelView()
                InterfazGrafica(miModelView)
            }
        }
    }
}