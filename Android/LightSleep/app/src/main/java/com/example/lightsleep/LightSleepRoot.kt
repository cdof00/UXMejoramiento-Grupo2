package com.example.lightsleep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lightsleep.screens.AlarmaCreada
import com.example.lightsleep.screens.CrearAlarma
import com.example.lightsleep.screens.Inicial
import com.example.lightsleep.screens.ListaPalabrasClave

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                LightSleepApp(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun LightSleepApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicial",
        modifier = modifier
    ) {
        composable("inicial") {
            Inicial(modifier = Modifier.fillMaxSize(),
                onCrearAlarma = { navController.navigate("crear_alarma") }
            )
        }
        composable("crear_alarma") {
            CrearAlarma(modifier = Modifier.fillMaxSize(),
                onVolver = { navController.popBackStack() },
                onCrear = { navController.navigate("alarma-creada") }
            )
        }
        composable("alarma-creada") {
            AlarmaCreada(modifier = Modifier.fillMaxSize(),
                onCrearAlarma = { navController.navigate("crear_alarma") }
            )
        }
    }
}
