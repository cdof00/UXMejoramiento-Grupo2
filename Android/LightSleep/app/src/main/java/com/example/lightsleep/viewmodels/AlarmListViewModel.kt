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


class AlarmListViewModel(application: Application) :  AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(AlarmListUiState())
    val uiState: StateFlow<AlarmListUiState> = _uiState


    init {

    }

}