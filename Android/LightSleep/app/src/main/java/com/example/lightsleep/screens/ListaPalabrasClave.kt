package com.example.lightsleep.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.lightsleep.R

@Composable
fun ListaPalabrasClave(
    modifier: Modifier = Modifier,
    onVolver: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .requiredWidth(width = 428.dp)
            .requiredHeight(height = 926.dp)
            .background(color = Color(0xff000000))
    ) {
        Text(
            text = "Palabras clave",
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 71.dp)
        )

        TypeRoundSizeSmallStateEnabled(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 15.dp, y = 50.dp),
            labelText = " Volver",
            textColor = MaterialTheme.colorScheme.inverseSurface,
            backgroundColor = MaterialTheme.colorScheme.inverseOnSurface,
            iconId = R.drawable.keyboard_return_24dp,
            onClick = onVolver
        )

        Column(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 158.dp)
                .requiredWidth(width = 428.dp)
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(vertical = 8.dp)
        ) {
            PalabraClaveItem(texto = "Despertar")
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            PalabraClaveItem(texto = "Luces")
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            PalabraClaveItem(texto = "Silencio")
        }

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .offset(x = (-20).dp, y = (-40).dp)
                .clip(shape = RoundedCornerShape(100.dp))
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Agregar palabra clave"
            )
            Text(text = "Agregar")
        }
    }
}

@Composable
fun PalabraClaveItem(texto: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(height = 56.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = texto,
            color = Color.White,
            fontSize = 20.sp,
            lineHeight = 1.43.em,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(widthDp = 428, heightDp = 926)
@Composable
private fun ListaPalabrasClavePreview() {
    ListaPalabrasClave(Modifier)
}
