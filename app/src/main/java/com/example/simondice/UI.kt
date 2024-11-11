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


@Composable
fun BotonColor(
    miModelView: ModelView,
    color: Colores,
    mostrarColorPulsado: (Colores) -> Unit,
    setTextoPartida: (String) -> Unit
) {
    var _boton by remember { mutableStateOf(miModelView.estadoLiveData.value!!.boton_color) }
    miModelView.estadoLiveData.observe(LocalLifecycleOwner.current){
        _boton = miModelView.estadoLiveData.value!!.boton_color
    }

    Button(
        //enabled = _boton,
        onClick = {
            if (_boton) {
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

@Composable
fun BotonStart(
    miModelView: ModelView,
    textoPartida: String
) {
    var _start by remember { mutableStateOf(miModelView.estadoLiveData.value!!.boton_start) }
    miModelView.estadoLiveData.observe(LocalLifecycleOwner.current){
        _start = miModelView.estadoLiveData.value!!.boton_start
    }

    var _color by remember { mutableStateOf(Color.White) }

    LaunchedEffect(_start) {
        while(_start){
            _color = Color.LightGray
            delay(200)
            _color = Color.White
            delay(1000)
        }
        _color = Color.White
    }

    Button(
        //enabled = _start,
        onClick = {
            if (_start) {
                miModelView.generarRandom()
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = _color,
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

@Composable
fun UI(miModelView: ModelView) {
    var textoPartida by remember { mutableStateOf("Nueva partida") }
    var colorSecuenciaActual by remember { mutableStateOf(Color.White) }

    var _estado by remember { mutableStateOf(miModelView.estadoLiveData.value) }
    miModelView.estadoLiveData.observe(LocalLifecycleOwner.current){
        _estado = miModelView.estadoLiveData.value
    }

    var _texto by remember { mutableStateOf(miModelView.estadoLiveData.value!!.texto) }
    miModelView.estadoLiveData.observe(LocalLifecycleOwner.current){
        _texto = miModelView.estadoLiveData.value!!.texto
    }

    fun mostrarColorPulsado(color: Colores){
        colorSecuenciaActual = color.color
        CoroutineScope(Dispatchers.Default).launch {
            delay(150)
            colorSecuenciaActual = Color.White
        }
    }

    @Composable
    fun CrearBotonColor(color: Colores){
        BotonColor(miModelView, color, ::mostrarColorPulsado, setTextoPartida = {textoPartida = it})
    }

    LaunchedEffect(_estado) {
        if (_estado == Estados.GENERANDO) {
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
                text = _texto,
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