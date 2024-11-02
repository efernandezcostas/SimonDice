package com.example.simondice

import android.util.Log
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Crea un botón con un color y un texto
 * @param miModelView Modelo de la vista
 * @param color Color del botón
 * @param empezarRespuesta Indica si se ha empezado la respuesta del usuario
 * @param setEmpezarRespuesta Función que cambia el valor de empezarRespuesta
 * @param empezarSecuencia Indica si se ha empezado la secuencia de la máquina
 * @param secuenciaCompleta Indica si la secuencia del usuario está completa
 * @param setSecuenciaCompleta Función que cambia el valor de secuenciaCompleta
 * @param setColorSecuenciaActual Función que cambia el color de la secuencia actual
 * @param setQuienDice Función que cambia el texto de quien dice
 */
@Composable
fun MiBoton(
    miModelView: ModelView,
    color: Colores,
    empezarRespuesta: Boolean,
    setEmpezarRespuesta: (Boolean) -> Unit,
    empezarSecuencia: Boolean,
    secuenciaCompleta: Boolean,
    setSecuenciaCompleta: (Boolean) -> Unit,
    setColorSecuenciaActual: (Color) -> Unit,
    setQuienDice: (String) -> Unit,
    setTextoPartida: (String) -> Unit
) {
    return Button(
        onClick = {
            if (!empezarSecuencia                           // impide que se pulse un nuevo color mientras se muestra la secuencia de la máquina
                && !empezarRespuesta                        // impide que se pulse un nuevo color mientras se muestra la secuencia del usuario
                && !secuenciaCompleta                       // impide que se pulse un nuevo color cuando la secuencia está completa (ronda terminada)
                && Datos.secuenciaMaquina.isNotEmpty()      // impide que se pulse un nuevo color cuando la secuencia de la máquina está vacía (Antes de nueva partida)
            ){
                setColorSecuenciaActual(color.color)
                setEmpezarRespuesta(true)

                var secuenciaCompletaLocal = false
                var respuestaCorrecta = miModelView.respuestaUsuario(color) { secuenciaCompletaLocal = it }         // Utiliza lambda para el parámetro setSecuenciaCompletaLocal al ser la última variable

                if (!respuestaCorrecta) {
                    setQuienDice("¡Has perdido en la ronda ${Datos.ronda}!")
                    setTextoPartida("Nueva partida")
                    miModelView.nuevaPartida()
                } else if (secuenciaCompletaLocal) {
                    setQuienDice("¡Ronda ${Datos.ronda} superada!")
                    setSecuenciaCompleta(true)
                }
            }
            Log.d("Secuencia", Datos.secuenciaMaquina.toString()+" - "+Datos.secuenciaUsuario.toString())

        },
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
    var textoPartida by remember { mutableStateOf("Nueva partida") }
    var colorSecuenciaActual by remember { mutableStateOf(Color.White) }

    var empezarRespuesta by remember { mutableStateOf(false) }
    var empezarSecuencia by remember { mutableStateOf(false) }
    var secuenciaCompleta by remember { mutableStateOf(true) }

    /**
     * Crea un botón con un color utilizando la función [MiBoton]
     * @param color Color del botón
     */
    @Composable
    fun CrearMiBoton(color: Colores) {
        MiBoton(
            miModelView = miModelView,
            color = color,
            empezarRespuesta = empezarRespuesta,
            setEmpezarRespuesta = { empezarRespuesta = it },
            empezarSecuencia = empezarSecuencia,
            secuenciaCompleta = secuenciaCompleta,
            setColorSecuenciaActual = { colorSecuenciaActual = it },
            setQuienDice = { quienDice = it },
            setSecuenciaCompleta = { secuenciaCompleta = it },
            setTextoPartida = { textoPartida = it },
        )
    }

    /*
        Se lanza cuando cambia el valor de empezarSecuencia. Si el nuevo valor es true, se muestra la secuencia de la máquina
     */
    LaunchedEffect(empezarSecuencia) {
        if (empezarSecuencia) {
            quienDice = "Simón Dice"

            for (color in Datos.secuenciaMaquina) {
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
                CrearMiBoton(Colores.AZUL)
                CrearMiBoton(Colores.VERDE)
            }
            Column {
                CrearMiBoton(Colores.ROJO)
                CrearMiBoton(Colores.AMARILLO)
            }
        }

        Spacer(modifier = Modifier.weight(1f))


        Row {
            /* Botón de empezar */
            Button(
                onClick = {
                    if (!empezarSecuencia && !empezarRespuesta) {
                        if (Datos.secuenciaMaquina.isNotEmpty() && !secuenciaCompleta) {
                            quienDice = "¡Faltan colores!"

                            /*
                            CoroutineScope(Dispatchers.Main).launch {
                                delay(1000)
                                quienDice = "Tú Dices"
                            }*/

                        } else {
                            miModelView.nuevaRonda()
                            textoPartida = "Siguiente ronda"
                            empezarSecuencia = true
                            secuenciaCompleta = false
                            Log.d("Secuencia", Datos.secuenciaMaquina.toString() + " - " + Datos.secuenciaUsuario.toString())
                        }
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
                    text = textoPartida,
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