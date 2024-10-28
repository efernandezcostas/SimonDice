package com.example.simondice

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.ui.theme.SimonDiceTheme
import kotlinx.coroutines.delay

@Composable
fun MiBoton(color: Colores, onClick2: () -> Unit) {
    return Button(
        onClick = onClick2,
        modifier = Modifier
            .padding(10.dp, 10.dp)
            .size(160.dp, 200.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color.color)
    ) {
        Text(
            text = color.nombre,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun UI(miModelView: ModelView) {
    var quienDice by remember { mutableStateOf("Simón Dice") }

    var colorSecuenciaActual by remember { mutableStateOf(Color.White) }
    var secuenciaColores by remember { mutableStateOf(mutableListOf<Colores>()) }
    var secuenciaUsuario by remember { mutableStateOf(mutableListOf<Colores>()) }
    var numeroSecuencia by remember { mutableIntStateOf(0) }

    var empezarRespuesta by remember { mutableStateOf(false) }
    var empezarSecuencia by remember { mutableStateOf(false) }

    LaunchedEffect(empezarSecuencia) {
        if (empezarSecuencia) {
            quienDice = "Simón Dice"

            for (color in secuenciaColores) {
                colorSecuenciaActual = color.color
                delay(1000)
                colorSecuenciaActual = Color.White
                delay(200)
            }
            colorSecuenciaActual = Color.White

            quienDice = "Tú Dices"

            empezarSecuencia = false
        }
    }

    LaunchedEffect(empezarRespuesta) {
        if (empezarRespuesta) {
            delay(300)
            colorSecuenciaActual = Color.White
            empezarRespuesta = false
        }
    }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .background(colorSecuenciaActual)
                .border(color = Color.Black, width = 1.dp)
                .padding(10.dp, 40.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = quienDice,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row {
            Column {
                MiBoton(
                    Colores.AZUL,
                    onClick2 = {
                        if (!empezarSecuencia && !empezarRespuesta){
                            numeroSecuencia++

                            if (secuenciaColores.size>=numeroSecuencia){
                                secuenciaUsuario.add(Colores.AZUL)
                                colorSecuenciaActual = Colores.AZUL.color
                                empezarRespuesta = true
                                miModelView.comprobarSecuencia(numeroSecuencia, secuenciaColores, secuenciaUsuario)
                            } else {
                                numeroSecuencia--
                            }
                        }
                    }
                )
                MiBoton(
                    Colores.VERDE,
                    onClick2 = {
                        if (!empezarSecuencia && !empezarRespuesta){
                            numeroSecuencia++

                            if (secuenciaColores.size>=numeroSecuencia){
                                secuenciaUsuario.add(Colores.VERDE)
                                colorSecuenciaActual = Colores.VERDE.color
                                empezarRespuesta = true
                                miModelView.comprobarSecuencia(numeroSecuencia, secuenciaColores, secuenciaUsuario)
                            } else {
                                numeroSecuencia--
                            }
                        }
                    }
                )
            }

            Column {
                MiBoton(
                    Colores.ROJO,
                    onClick2 = {
                        if (!empezarSecuencia && !empezarRespuesta){
                            numeroSecuencia++

                            if (secuenciaColores.size>=numeroSecuencia){
                                secuenciaUsuario.add(Colores.ROJO)
                                colorSecuenciaActual = Colores.ROJO.color
                                empezarRespuesta = true
                                miModelView.comprobarSecuencia(numeroSecuencia, secuenciaColores, secuenciaUsuario)
                            } else {
                                numeroSecuencia--
                            }
                        }
                    }
                )
                MiBoton(
                    Colores.AMARILLO,
                    onClick2 = {
                        if (!empezarSecuencia && !empezarRespuesta){
                            numeroSecuencia++

                            if (secuenciaColores.size>=numeroSecuencia){
                                secuenciaUsuario.add(Colores.AMARILLO)
                                colorSecuenciaActual = Colores.AMARILLO.color
                                empezarRespuesta = true
                                miModelView.comprobarSecuencia(numeroSecuencia, secuenciaColores, secuenciaUsuario)
                            } else {
                                numeroSecuencia--
                            }
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row {
            Button(
                onClick = {
                    if (!empezarSecuencia && !empezarRespuesta) {
                        miModelView.crearRandom()
                        when(Datos.numero){
                            1 -> secuenciaColores.add(Colores.AZUL)
                            2 -> secuenciaColores.add(Colores.ROJO)
                            3 -> secuenciaColores.add(Colores.VERDE)
                            4 -> secuenciaColores.add(Colores.AMARILLO)
                        }

                        empezarSecuencia = true
                        numeroSecuencia = 0
                        secuenciaUsuario.clear()
                    }
                },
                modifier = Modifier
                    .border(color = Color.Black, width = 1.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = "Empezar",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InterfazGraficaPreview() {
    SimonDiceTheme {
        UI(ModelView())
    }
}