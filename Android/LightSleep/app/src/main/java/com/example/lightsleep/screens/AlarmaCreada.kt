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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Stars
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.em
import com.example.lightsleep.R

@Composable
fun AlarmaCreada(modifier: Modifier = Modifier) {
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
        BotonAgregarMini(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 262.dp,
                    y = 85.dp))
        ListItem(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 158.dp))
    }
}

@Composable
fun BotonAgregarMini(modifier: Modifier = Modifier) {
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
fun ListItem(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .requiredWidth(width = 428.dp)
            .requiredHeight(height = 98.dp)
            .background(color = darkColorScheme().secondaryContainer)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp,
                    vertical = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(weight = 1f)
            ) {
                TypeText(
                    supportingText = "",
                    labelText = "10:00 AM | L, M, J",
                    overlineText = "Alarma 1",
                )
            }
            TypeSwitch()
        }
    }
}


@Composable
fun TypeText(modifier: Modifier = Modifier, supportingText: String, labelText: String, overlineText: String) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = overlineText,
            color = lightColorScheme().surfaceVariant,
            lineHeight = 1.27.em,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.CenterVertically))

        Text(
            text = labelText,
            color = lightColorScheme().surface,
            lineHeight = 1.25.em,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.CenterVertically))

        Text(
            text = supportingText,
            color = lightColorScheme().onSurfaceVariant,
            lineHeight = 1.43.em,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth())

    }
}

@Composable
fun TypeSwitch(modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        colors = SwitchDefaults.colors(checkedTrackColor = Color(0xff8DFF54)),
        onCheckedChange = {
            checked = it
        },
        thumbContent = if (checked) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        } else {
            null
        }
    )
}

@Composable
fun SelectedTrueStateEnabledIconTrue(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 52.dp)
            .requiredHeight(height = 32.dp)
            .clip(shape = RoundedCornerShape(100.dp))
            .background(color = Color(0xff8dff54))
            .padding(horizontal = 4.dp,
                vertical = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
                    .offset(x = 12.dp,
                        y = 0.dp)
                    .padding(all = 4.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(100.dp))
                        .padding(all = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 24.dp)
                            .clip(shape = RoundedCornerShape(24.dp))
                            .background(color = lightColorScheme().onPrimary)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(all = 11.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 2.dp)
                                    .clip(shape = RoundedCornerShape(23.dp)))
                        }
                        Image(
                            painter = painterResource(id = R.drawable.check_24dp),
                            contentDescription = "Icon",
                            colorFilter = ColorFilter.tint(lightColorScheme().onPrimaryContainer),
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 16.dp))
                    }
                }
            }
        }
    }
}


@Preview(widthDp = 428, heightDp = 926)
@Composable
private fun InicialPreview() {
    AlarmaCreada(Modifier)
}