package com.example.lightsleep.viewmodels


import android.app.Application
import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lightsleep.models.Alarm
import com.example.lightsleep.states.AlarmListUiState
import com.example.lightsleep.states.AlarmUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AlarmViewModel(application: Application) :  AndroidViewModel(application) {

    var alarmNameState = TextFieldState()

    private val _alarmHour = MutableStateFlow(-1)
    val alarmHour: StateFlow<Int> = _alarmHour.asStateFlow()

    private val _alarmMinute = MutableStateFlow(-1)
    val alarmMinute: StateFlow<Int> = _alarmMinute.asStateFlow()

    private val _alarmState = MutableStateFlow(AlarmUiState())
    val alarmState: StateFlow<AlarmUiState> = _alarmState.asStateFlow()

    init {
        alarmNameState = TextFieldState("Alarma 1")
        _alarmState.update { AlarmUiState (Alarm(0,"","","","","",0,0,emptyList())
            ) }
    }

    fun saveAlarm(){

    }

    fun updateHour(hora: Int) {
        _alarmHour.value = hora
    }

    fun updateMinute(hora: Int) {
        _alarmMinute.value = hora
    }

}