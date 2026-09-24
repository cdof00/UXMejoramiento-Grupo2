package com.example.lightsleep.screens

import android.icu.util.Calendar
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
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.TextRange
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lightsleep.R
import com.example.lightsleep.viewmodels.AlarmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearAlarma(modifier: Modifier = Modifier, alarmViewModel: AlarmViewModel = viewModel()) {

    val alarmUiState by alarmViewModel.alarmState.collectAsState()
    val alarmHourState by alarmViewModel.alarmHour.collectAsState()
    val alarmMinuteState by alarmViewModel.alarmMinute.collectAsState()

    val openTimeDialog = remember { mutableStateOf(false) }
    val openFreqDialog = remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .requiredWidth(width = LocalWindowInfo.current.containerDpSize.width)
            .requiredHeight(height = LocalWindowInfo.current.containerDpSize.height)
            .background(color = darkColorScheme().surfaceContainerLowest)
    ) {
        if(openTimeDialog.value){

            val currentTime = Calendar.getInstance()
            val hour = if (alarmHourState != -1) alarmHourState else currentTime.get(Calendar.HOUR_OF_DAY)
            val minute = if (alarmMinuteState != -1) alarmMinuteState else currentTime.get(Calendar.MINUTE)
            val timePickerState = rememberTimePickerState(
                initialHour = hour,
                initialMinute = minute,
                is24Hour = false,
            )

            TimeDialog(
                onDismissRequest = { openTimeDialog.value = false },
                onConfirmation = {
                    openTimeDialog.value = false
                    alarmViewModel.updateHour(timePickerState.hour)
                    alarmViewModel.updateMinute(timePickerState.minute)
                },
                dialogTitle = "Ingrese hora",
                timePickerState = timePickerState
            )
        }
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
                .requiredWidth(width = LocalWindowInfo.current.containerDpSize.width)
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 0.dp,
                    y = 164.dp
                ),
            state = alarmViewModel.alarmNameState,
            lineLimits = TextFieldLineLimits.SingleLine,
            label = { Text("Nombre de alarma") },
            shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            ),
            trailingIcon = {
                if (alarmViewModel.alarmNameState.text.isNotEmpty()) {
                    IconButton(onClick = { alarmViewModel.alarmNameState.edit {
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
                    handleColor = darkColorScheme().primary,
                    backgroundColor = darkColorScheme().primary
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
            ),
            openTimeDialog)
        TypeRoundSizeSmallStateEnabled(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 281.dp,
                    y = 851.dp
                ),
            labelText = "Guardar",
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
            labelText = "Cancelar",
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

            ) {
            Button(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(100.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                onClick = { }) {
                ButtonIcon(iconId = iconId)
                Text(
                    text = labelText,
                    color = textColor,
                    lineHeight = 1.43.em,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )

            }
        }
    }
}

@Composable
fun ThemeStandardGroups1(modifier: Modifier = Modifier, openTimeDialog: MutableState<Boolean>) {

    Row(
        modifier = modifier
            .requiredWidth(width = LocalWindowInfo.current.containerDpSize.width)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = darkColorScheme().inverseOnSurface)
            .padding(vertical = 10.dp)
    ) {
        Column(
        ) {
            DropdownMenuItem(
                modifier = Modifier.padding(top=10.dp),
                text = { Text(text = "Hora", style = MaterialTheme.typography.bodyLarge, color = lightColorScheme().surface) },
                leadingIcon = { Icon(Icons.Outlined.Alarm, contentDescription = null, tint = darkColorScheme().primary) },
                trailingIcon = { Icon(Icons.Outlined.PlayArrow, contentDescription = null, tint = darkColorScheme().primary) },
                onClick = { openTimeDialog.value = true }
            )
            HorizontalFullwidth()
            DropdownMenuItem(
                modifier = Modifier.padding( top=30.dp),
                text = { Text(text = "Frecuencia", style = MaterialTheme.typography.bodyLarge, color = lightColorScheme().surface) },
                leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = null, tint = darkColorScheme().primary) },
                trailingIcon = { Icon(Icons.Outlined.PlayArrow, contentDescription = null, tint = darkColorScheme().primary) },
                onClick = { openTimeDialog.value = true }
            )
            HorizontalFullwidth()
            DropdownMenuItem(
                modifier = Modifier.padding( top=30.dp),
                text = { Text(text = "Activa Por", style = MaterialTheme.typography.bodyLarge, color = lightColorScheme().surface) },
                leadingIcon = { Icon(Icons.Outlined.Today, contentDescription = null, tint = darkColorScheme().primary) },
                trailingIcon = { Icon(Icons.Outlined.PlayArrow, contentDescription = null, tint = darkColorScheme().primary) },
                onClick = { openTimeDialog.value = true }
            )
            HorizontalFullwidth()
            DropdownMenuItem(
                modifier = Modifier.padding( top=30.dp),
                text = { Text(text = "Opciones de Bombilllo", style = MaterialTheme.typography.bodyLarge, color = lightColorScheme().surface) },
                leadingIcon = { Icon(Icons.Outlined.LightMode, contentDescription = null, tint = darkColorScheme().primary) },
                trailingIcon = { Icon(Icons.Outlined.PlayArrow, contentDescription = null, tint = darkColorScheme().primary) },
                onClick = { openTimeDialog.value = true }
            )
            HorizontalFullwidth()
            DropdownMenuItem(
                modifier = Modifier.padding( top=30.dp),
                text = { Text(text = "Palabra Clave", style = MaterialTheme.typography.bodyLarge, color = lightColorScheme().surface) },
                leadingIcon = { Icon(Icons.Outlined.TextFields, contentDescription = null, tint = darkColorScheme().primary) },
                trailingIcon = { Icon(Icons.Outlined.PlayArrow, contentDescription = null, tint = darkColorScheme().primary) },
                onClick = { openTimeDialog.value = true }
            )
            HorizontalFullwidth()
        }
    }
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
fun ButtonIcon(modifier: Modifier = Modifier, iconId: Int) {
    Icon(
        painter = painterResource(id = iconId),
        contentDescription = "Icon",
        tint = darkColorScheme().onPrimaryContainer,
        modifier = Modifier
            .requiredSize(size = 20.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerAlarma(
    timePickerState: TimePickerState
) {
    Column {
        TimeInput(
            state = timePickerState,
            colors = TimePickerDefaults.colors(
                containerColor = darkColorScheme().primary,
                periodSelectorBorderColor = darkColorScheme().outline,
                periodSelectorUnselectedContainerColor = darkColorScheme().surfaceContainerHigh,
                periodSelectorSelectedContainerColor = darkColorScheme().tertiaryContainer,
                periodSelectorSelectedContentColor = darkColorScheme().onTertiaryContainer,
                periodSelectorUnselectedContentColor = darkColorScheme().onSurfaceVariant,
                timeSelectorSelectedContainerColor = darkColorScheme().primaryContainer,
                timeSelectorUnselectedContainerColor = darkColorScheme().surfaceContainerHighest,
                timeSelectorSelectedContentColor = darkColorScheme().onPrimaryContainer,
                timeSelectorUnselectedContentColor = darkColorScheme().onSurface
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    timePickerState: TimePickerState
) {
    AlertDialog(
        containerColor = darkColorScheme().surfaceContainerHigh,
        titleContentColor = darkColorScheme().onSurfaceVariant,
        title = {
            Text(text = dialogTitle)
        },
        text = {TimePickerAlarma(
            timePickerState
        )},
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = "Guardar", color = darkColorScheme().primary)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = "Cancelar", color = darkColorScheme().primary)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreqDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    timePickerState: TimePickerState
) {
    AlertDialog(
        containerColor = darkColorScheme().surfaceContainerHigh,
        titleContentColor = darkColorScheme().onSurfaceVariant,
        title = {
            Text(text = dialogTitle)
        },
        text = {TimePickerAlarma(
            timePickerState
        )},
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = "Guardar", color = darkColorScheme().primary)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = "Cancelar", color = darkColorScheme().primary)
            }
        }
    )
}

@Preview(widthDp = 428, heightDp = 926)
@Composable
private fun CrearAlarmaPreview() {
    CrearAlarma(Modifier)
}