package com.example.lightsleep.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.material3.lightColorScheme
import com.example.lightsleep.R

@Composable
fun Inicial(
    modifier: Modifier = Modifier,
    onCrearAlarma: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .requiredWidth(width = 428.dp)
            .requiredHeight(height = 926.dp)
            .background(color = Color(0xff000000))
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondoappmejorado1),
            contentDescription = "fondo app mejorado 1",
            alpha = 0.2F,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 3.dp,
                    y = 85.dp)
                .requiredWidth(width = 423.dp)
                .requiredHeight(height = 845.dp))
        Text(
            text = "Alarmas",
            color = Color.White,
            style = TextStyle(
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp,
                    y = 71.dp)
                .requiredWidth(width = 185.dp)
                .requiredHeight(height = 56.dp))
        BotonAgregarGrande(
            onClick = onCrearAlarma,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 166.dp,
                    y = 431.dp))
        Text(
            text = "Crear",
            color = Color.White,
            style = TextStyle(
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 154.dp,
                    y = 527.dp)
                .requiredWidth(width = 119.dp)
                .requiredHeight(height = 45.dp))
    }
}

@Composable
fun BotonAgregarGrande(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .requiredWidth(width = 96.dp)
                .clip(shape = RoundedCornerShape(100.dp))
                .background(color = Color(0xff705dda))
        ) {
            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 96.dp)
                    .padding(all = 32.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add_24dp),
                    contentDescription = "Icon",
                    colorFilter = ColorFilter.tint(lightColorScheme().onSecondary),
                    modifier = Modifier
                        .requiredSize(size = 64.dp))
            }
        }
    }
}

@Preview(widthDp = 428, heightDp = 926)
@Composable
private fun InicialPreview() {
    Inicial(Modifier)
}