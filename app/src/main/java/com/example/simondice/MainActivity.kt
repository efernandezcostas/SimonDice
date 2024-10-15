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


package com.example.simondice

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.ui.theme.SimonDiceTheme
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimonDiceTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ){
                    val secuenciaJuego = remember { mutableListOf<Int>() }
                    InterfazBotones(secuenciaJuego)
                }
            }
        }

        val text = "Esto es un toast"
        val duracion = Toast.LENGTH_LONG

        val toast = Toast.makeText(this, text, duracion)
        toast.show()
    }
}

enum class Colores(val color: Color, val nombre: String, val id: Int) {
    AZUL(Color(59, 59, 197, 255), "Azul", 1),
    ROJO(Color(180, 0, 0, 255), "Rojo", 2),
    VERDE(Color(5, 162, 5, 255), "Verde", 3),
    AMARILLO(Color(207, 207, 28, 255), "Amarillo", 4)
}

fun comprobarAciertos(listaJuego: MutableList<Int>, listaJugador: MutableList<Int>) {

}

//fun crearSecuenciaJuego(lista: MutableList<Int>) {
//    lista.add(Random.nextInt(1,5))
//}

@Composable
fun InterfazBotones(secuenciaJuego: MutableList<Int>) {
    secuenciaJuego.add(Random.nextInt(1,5))


    val secuenciaJugador = remember { mutableListOf<Int>() }
    val puntuacion by remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Top,
    ){
        Text(
            fontSize = 21.sp,
            text = "Puntuación: $puntuacion",
            modifier = Modifier
                .padding(16.dp),
            color = Color.Black
        )
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Button(
                onClick = {
                    secuenciaJugador.add(Colores.AZUL.id)
                    Log.d("Secuencia", secuenciaJuego.toString())
                },
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Colores.AZUL.color),
                shape = RectangleShape,
            ) {
                Text(
                    text = Colores.AZUL.nombre,
                    fontSize = 20.sp
                )
            }
            Button(
                onClick = {
                    secuenciaJuego.add(Random.nextInt(1,5))
                },
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Colores.ROJO.color),
                shape = RectangleShape,
            ) {
                Text(
                    text = "Rojo",
                    fontSize = 20.sp
                )
            }
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Button(
                onClick = {
                    secuenciaJuego.add(Random.nextInt(1,5))
                },
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Colores.VERDE.color),
                shape = RectangleShape,
            ) {
                Text(
                    text = "Verde",
                    fontSize = 20.sp
                )
            }
            Button(
                onClick = {

                },
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Colores.AMARILLO.color),
                shape = RectangleShape,
            ) {
                Text(
                    text = "Amarillo",
                    fontSize = 20.sp
                )
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ){
        Button(
            onClick = {  },
            modifier = Modifier
                .padding(16.dp)
                .aspectRatio(4f)
                .border(1.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
            shape = RectangleShape
        ) {
            Text(
                text = "Empezar",
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}
