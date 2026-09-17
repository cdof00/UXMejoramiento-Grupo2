package com.example.lightsleep

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
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

@Composable
fun Inicial(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 428.dp)
            .requiredHeight(height = 926.dp)
            .background(color = Color(0xff000000))
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondoappmejorado1),
            contentDescription = "fondo app mejorado 1",
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
        Text(
            text = "Crear",
            color = Color.White,
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 316.dp,
                    y = 85.dp)
                .requiredWidth(width = 83.dp)
                .requiredHeight(height = 30.dp))
        TypeRoundSizeSmallWidthDefaultStateEnabled(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 262.dp,
                    y = 85.dp))
        Text(
            text = "No tiene alarmas",
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 93.dp,
                    y = 388.dp)
                .requiredWidth(width = 244.dp)
                .requiredHeight(height = 31.dp))
        TypeRoundSizeLargeWidthDefaultStateEnabled(
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
fun TypeRoundSizeSmallWidthDefaultStateEnabled(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 53.dp)
            .requiredHeight(height = 39.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .requiredWidth(width = 40.dp)
                .clip(shape = RoundedCornerShape(100.dp))
                .background(color = Color(0xff705dda))
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(all = 10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .requiredSize(size = 40.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_24dp),
                        contentDescription = "Icon",
                        tint = lightColorScheme().onSecondary)
                }
            }
        }
    }
}

@Composable
fun TypeRoundSizeLargeWidthDefaultStateEnabled(modifier: Modifier = Modifier) {
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
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
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
                        .requiredSize(size = 32.dp))
            }
        }
    }
}

@Preview(widthDp = 428, heightDp = 926)
@Composable
private fun InicialPreview() {
    Inicial(Modifier)
}