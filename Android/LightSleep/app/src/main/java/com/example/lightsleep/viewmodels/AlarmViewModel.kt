package com.example.lightsleep.viewmodels


import android.app.Application
import android.util.Log
import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.lightsleep.states.AlarmListUiState
import com.example.lightsleep.states.AlarmUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AlarmViewModel(application: Application) :  AndroidViewModel(application) {

    var alarmNameState = TextFieldState()

    private val _uiState = MutableStateFlow(AlarmListUiState())
    val uiState: StateFlow<AlarmListUiState> = _uiState

    private val _alarmState = MutableStateFlow(AlarmUiState())
    val alarmState: StateFlow<AlarmUiState> = _alarmState.asStateFlow()

    val isLoading = MutableStateFlow(true)

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        alarmNameState = TextFieldState("Alarma 1")
    }
}