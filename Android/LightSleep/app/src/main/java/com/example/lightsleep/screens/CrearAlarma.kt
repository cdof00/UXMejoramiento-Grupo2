package com.example.lightsleep.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.em
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.TextRange
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lightsleep.R
import com.example.lightsleep.viewmodels.AlarmViewModel

@Composable
fun CrearAlarma(modifier: Modifier = Modifier, alarmViewModel: AlarmViewModel = viewModel()) {
    val width = LocalWindowInfo.current.containerDpSize.width

    val alarmUiState by alarmViewModel.alarmState.collectAsState()

    Box(
        modifier = modifier
            .requiredWidth(width = width)
            .requiredHeight(height = LocalWindowInfo.current.containerDpSize.height)
            .background(color = darkColorScheme().surfaceContainerLowest)
    ) {
        TypeRoundSizeSmallStateEnabled(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 15.dp,
                    y = 50.dp
                ),
            labelText = "Volver",
            textColor = darkColorScheme().inverseSurface,
            backgroundColor = darkColorScheme().inverseOnSurface,
            iconId = R.drawable.keyboard_return_24dp
        )

        OutlinedTextField(
            modifier = Modifier
                .requiredWidth(width = width)
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 0.dp,
                    y = 164.dp
                ),
            state = alarmViewModel.usernameState,
            lineLimits = TextFieldLineLimits.SingleLine,
            label = { Text("Nombre de alarma") },
            shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            ),
            trailingIcon = {
                if (alarmViewModel.usernameState.text.isNotEmpty()) {
                    IconButton(onClick = { alarmViewModel.usernameState.edit {
                        replace(0, length, "")
                     }}) {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Clear text"
                        )
                    }
                }
            },
            colors = TextFieldColors(
                focusedTextColor = darkColorScheme().inverseSurface,
                unfocusedTextColor = darkColorScheme().inverseSurface,
                disabledTextColor = darkColorScheme().inverseSurface,
                errorTextColor = darkColorScheme().inverseSurface,
                focusedContainerColor = darkColorScheme().inverseOnSurface,
                unfocusedContainerColor = darkColorScheme().inverseOnSurface,
                disabledContainerColor = darkColorScheme().inverseOnSurface,
                errorContainerColor = darkColorScheme().inverseOnSurface,
                cursorColor = darkColorScheme().primary,
                errorCursorColor = darkColorScheme().primary,
                textSelectionColors = TextSelectionColors(
                    handleColor = darkColorScheme().inverseOnSurface,
                    backgroundColor = darkColorScheme().inverseOnSurface
                ),
                focusedIndicatorColor = darkColorScheme().primary,
                unfocusedIndicatorColor = darkColorScheme().primary,
                disabledIndicatorColor = darkColorScheme().primary,
                errorIndicatorColor = darkColorScheme().primary,
                focusedLeadingIconColor = darkColorScheme().primary,
                unfocusedLeadingIconColor = darkColorScheme().primary,
                disabledLeadingIconColor = darkColorScheme().primary,
                errorLeadingIconColor = darkColorScheme().primary,
                focusedTrailingIconColor = darkColorScheme().primary,
                unfocusedTrailingIconColor = darkColorScheme().primary,
                disabledTrailingIconColor = darkColorScheme().primary,
                errorTrailingIconColor = darkColorScheme().primary,
                focusedLabelColor = darkColorScheme().primary,
                unfocusedLabelColor = darkColorScheme().primary,
                disabledLabelColor = darkColorScheme().primary,
                errorLabelColor = darkColorScheme().primary,
                focusedPlaceholderColor = darkColorScheme().primary,
                unfocusedPlaceholderColor = darkColorScheme().primary,
                disabledPlaceholderColor = darkColorScheme().primary,
                errorPlaceholderColor = darkColorScheme().primary,
                focusedSupportingTextColor = darkColorScheme().primary,
                unfocusedSupportingTextColor = darkColorScheme().primary,
                disabledSupportingTextColor = darkColorScheme().primary,
                errorSupportingTextColor = darkColorScheme().primary,
                focusedPrefixColor = darkColorScheme().primary,
                unfocusedPrefixColor = darkColorScheme().primary,
                disabledPrefixColor = darkColorScheme().primary,
                errorPrefixColor = darkColorScheme().primary,
                focusedSuffixColor = darkColorScheme().primary,
                unfocusedSuffixColor = darkColorScheme().primary,
                disabledSuffixColor = darkColorScheme().primary,
                errorSuffixColor = darkColorScheme().primary
            )
        )

        ThemeStandardGroups1(modifier = Modifier
            .align(alignment = Alignment.TopStart)
            .offset(
                x = 0.dp,
                y = 287.dp
            ),)
        TypeRoundSizeSmallStateEnabled(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 281.dp,
                    y = 851.dp
                ),
            labelText = "Label 3",
            textColor = darkColorScheme().inverseSurface,
            backgroundColor = darkColorScheme().inversePrimary,
            iconId = R.drawable.check_24dp

        )
        TypeRoundSizeSmallStateEnabled(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 41.dp,
                    y = 851.dp
                ),
            labelText = "Label 4",
            textColor = darkColorScheme().inverseSurface,
            backgroundColor = darkColorScheme().tertiaryContainer,
            iconId = R.drawable.close_24dp
        )
    }
}

@Composable
fun TypeRoundSizeSmallStateEnabled(modifier: Modifier = Modifier, labelText: String, textColor: Color, backgroundColor: Color, iconId: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredHeight(height = 48.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(100.dp))
                .background(color = backgroundColor)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp,
                        vertical = 10.dp)
            ) {
                Close(iconId = iconId)
                Text(
                    text = labelText,
                    color = textColor,
                    lineHeight = 1.43.em,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
        }
    }
}

@Composable
fun ThemeStandardGroups1(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .requiredWidth(width = LocalWindowInfo.current.containerDpSize.width)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = darkColorScheme().inverseOnSurface)
            .padding(vertical = 10.dp)
    ) {
        Column(
        ) {
            ElementoMenuCrearAlarma(modifier = Modifier.padding(bottom=20.dp, top=10.dp),titulo ="Hora", icon = R.drawable.alarm_24dp, onClick = {})
            ElementoMenuCrearAlarma(modifier = Modifier.padding(bottom=20.dp, top=20.dp),titulo="Frecuencia", icon = R.drawable.edit_24dp, onClick = {})
            ElementoMenuCrearAlarma(modifier = Modifier.padding(bottom=20.dp, top=20.dp),titulo="Activa por", icon = R.drawable.edit_24dp, onClick = {})
            ElementoMenuCrearAlarma(modifier = Modifier.padding(bottom=20.dp, top=20.dp),titulo="Palabra 4", icon = R.drawable.edit_24dp, onClick = {})
            ElementoMenuCrearAlarma(modifier = Modifier.padding(bottom=10.dp,top=20.dp),titulo="Palabra 5", icon = R.drawable.edit_24dp, onClick = {})
        }
    }
}


@Composable
fun ElementoMenuCrearAlarma(modifier: Modifier = Modifier, titulo:String, icon: Int, onClick:() -> Unit) {
    Column(
        modifier = modifier
            .requiredWidth(width = LocalWindowInfo.current.containerDpSize.width)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(height = 40.dp)
                .padding(horizontal = 12.dp,
                    vertical = 8.dp)
        ) {
            LeadingIcon(id = icon, color = darkColorScheme().primary)
            Column(
                modifier = Modifier
                    .weight(weight = 1f)
            ) {
                Text(
                    text = titulo,
                    color = lightColorScheme().surface,
                    lineHeight = 1.5.em,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
            TrailingIcon(id = R.drawable.play_arrow_24dp, color = darkColorScheme().primary)
        }
        HorizontalFullwidth()
    }
}

@Composable
fun LeadingIcon(modifier: Modifier = Modifier, id: Int, color: Color ) {
    Icon(
        painter = painterResource(id = id),
        contentDescription = "Icon",
        tint = color,
        modifier = Modifier
            .requiredSize(size = 24.dp))

}

@Composable
fun TrailingIcon(modifier: Modifier = Modifier, id: Int, color: Color ) {
    Icon(
        painter = painterResource(id = id),
        contentDescription = "Icon",
        tint = color,
        modifier = Modifier
            .requiredSize(size = 24.dp))

}

@Composable
fun HorizontalFullwidth(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        thickness = DividerDefaults.Thickness,
        color = DividerDefaults.color
    )

}

@Composable
fun Close(modifier: Modifier = Modifier, iconId: Int) {
    Icon(
        painter = painterResource(id = iconId),
        contentDescription = "Icon",
        tint = darkColorScheme().onPrimaryContainer,
        modifier = Modifier
            .requiredSize(size = 20.dp))
}

@Preview(widthDp = 428, heightDp = 926)
@Composable
private fun CrearAlarmaPreview() {
    CrearAlarma(Modifier)
}