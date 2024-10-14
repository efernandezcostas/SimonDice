package com.example.simondice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Composable
fun Botones() {

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Button(
                onClick = {

                },
                modifier = Modifier
                    .size(150.dp, 80.dp)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0,0,150))
            ) {
                Text(
                    text = "Azul"
                )
            }
            Button(
                onClick = {

                },
                modifier = Modifier
                    .size(150.dp, 80.dp)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(150,0,0))
            ) {
                Text(
                    text = "Rojo"
                )
            }
        }

        Column {
            Button(
                onClick = {

                },
                modifier = Modifier
                    .size(150.dp, 80.dp)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0,150,0))
            ) {
                Text(
                    text = "Verde"
                )
            }
            Button(
                onClick = {

                },
                modifier = Modifier
                    .size(150.dp, 80.dp)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(204,204,0))
            ) {
                Text(
                    text = "Amarillo"
                )
            }
        }
    }
}
