// enum - data class - mutableList - remember


package com.example.simondice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
                    Botones()
                }
            }
        }
    }
}

enum class Colores(val r: Int, val g: Int, val b: Int) {
    AZUL(0, 0 , 150),
    ROJO(150, 0,0),
    VERDE(0, 150, 0),
    AMARILLO(204, 204, 0)
}

@Composable
fun SimonDice() {

}

@Composable
fun Botones() {
    var listaColores = null
    var puntuacion by remember { mutableIntStateOf(0) }

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
                    puntuacion++
                },
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Colores.AZUL),
                shape = RectangleShape,
            ) {
                Text(
                    text = "Azul",
                    fontSize = 20.sp
                )
            }
            Button(
                onClick = {
                    puntuacion--
                },
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(150,0,0)),
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

                },
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0,150,0)),
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
                colors = ButtonDefaults.buttonColors(containerColor = Color(204,204,0)),
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
            onClick = { puntuacion = 0 },
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
