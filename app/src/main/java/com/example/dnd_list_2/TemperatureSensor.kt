package com.example.dnd_list_2

import android.hardware.Sensor
import android.hardware.SensorManager

class TemperatureSensor(sensorManager: SensorManager, onSense:(SensorResult)-> Unit):Sensable(sensorManager,onSense) {
    override val type: Int
        get() = Sensor.TYPE_AMBIENT_TEMPERATURE
}