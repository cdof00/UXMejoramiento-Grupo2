package com.example.lightsleep.models

data class Alarm (
    val alarmId:Int,
    val name:String,
    val time:String,
    val frequency:String,
    val customFrequency:String,
    val validTime:String,
    val bulbBrightness:Int,
    val bulbTime: Int,
    val keyWords:List<String>
)
