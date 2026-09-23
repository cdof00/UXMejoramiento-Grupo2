package com.example.lightsleep.states

import com.example.lightsleep.models.Alarm

data class AlarmListUiState (
    val tracks: List<Alarm> = emptyList()
)

data class AlarmUiState (
    val alarm: Alarm = Alarm(0,"","","","","",0,0,emptyList())
)