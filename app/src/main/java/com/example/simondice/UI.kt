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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
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
 * Composable para crear los botones de colores
 */
@Composable
fun BotonColor(
    miModelView: ModelView,
    color: Colores,
    mostrarColorPulsado: (Colores) -> Unit,
    setTextoPartida: (String) -> Unit
) {
    var estadoBoton by remember { mutableStateOf(miModelView.estadoLiveData.value!!.botonColor) }
    miModelView.estadoLiveData.observe(LocalLifecycleOwner.current){
        estadoBoton = miModelView.estadoLiveData.value!!.botonColor
    }

    Button(
        //enabled = _boton,
        onClick = {
            if (estadoBoton) {
                miModelView.addASecuenciaUser(color, setTextoPartida)
                mostrarColorPulsado(color)
            }
        },
        colors = ButtonDefaults.buttonColors(containerColor = color.color),
        modifier = Modifier
            .padding(10.dp, 10.dp)
            .size(160.dp, 200.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = color.nombre,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Composable para crear el botón de start
 */
@Composable
fun BotonStart(
    miModelView: ModelView,
    textoPartida: String
) {
    var estadoStart by remember { mutableStateOf(miModelView.estadoLiveData.value!!.botonStart) }
    miModelView.estadoLiveData.observe(LocalLifecycleOwner.current){
        estadoStart = miModelView.estadoLiveData.value!!.botonStart
    }

    var colorStart by remember { mutableStateOf(Color.White) }

    /**
     *  Launched effect que hace que el botón start parpadee
     */
    LaunchedEffect(estadoStart) {
        while(estadoStart){
            colorStart = Color.LightGray
            delay(200)
            colorStart = Color.White
            delay(1000)
        }
        colorStart = Color.White
    }

    Button(
        //enabled = _start,
        onClick = {
            if (estadoStart) {
                miModelView.generarRandom()
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorStart,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .border(color = Color.Black, width = 1.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = textoPartida,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

/**
 * Composable que contiene la interfaz gráfica de la aplicación
 */
@Composable
fun UI(miModelView: ModelView) {
    var textoPartida by remember { mutableStateOf("Nueva partida") }
    var colorSecuenciaActual by remember { mutableStateOf(Color.White) }

    var estado by remember { mutableStateOf(miModelView.estadoLiveData.value) }
    miModelView.estadoLiveData.observe(LocalLifecycleOwner.current){
        estado = miModelView.estadoLiveData.value
    }

    var estadoTexto by remember { mutableStateOf(miModelView.estadoLiveData.value!!.texto) }
    miModelView.estadoLiveData.observe(LocalLifecycleOwner.current){
        estadoTexto = miModelView.estadoLiveData.value!!.texto
    }

    /**
     * Función que muestra el color pulsado durante 150 ms
     */
    fun mostrarColorPulsado(color: Colores){
        colorSecuenciaActual = color.color
        CoroutineScope(Dispatchers.Default).launch {
            delay(150)
            colorSecuenciaActual = Color.White
        }
    }

    /**
     * Función que crea un botón de color
     */
    @Composable
    fun CrearBotonColor(color: Colores){
        BotonColor(miModelView, color, ::mostrarColorPulsado, setTextoPartida = {textoPartida = it})
    }

    /**
     * Launched effect que muestra la secuencia de la máquina
     */
    LaunchedEffect(estado) {
        if (estado == Estados.GENERANDO) {
            for (color in Datos.secuenciaMaquina) {
                colorSecuenciaActual = color.color
                delay(1000)
                colorSecuenciaActual = Color.White
                delay(200)
            }
            colorSecuenciaActual = Color.White

            miModelView.estadoLiveData.value = Estados.RESPONDIENDO
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
                text = estadoTexto,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row {
            Column {
                CrearBotonColor(Colores.AZUL)
                CrearBotonColor(Colores.VERDE)
            }
            Column {
                CrearBotonColor(Colores.ROJO)
                CrearBotonColor(Colores.AMARILLO)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row {
            BotonStart(miModelView, textoPartida)
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