package com.example.dnd_list_2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dnd_list_2.databinding.FragmentStartBinding

class StartFragment : Fragment(R.layout.fragment_start), SensorEventListener {

    private lateinit var binding: FragmentStartBinding
    private var listFragment = ListFragment()
    private lateinit var sensorManager: SensorManager
    private var temperature: Sensor? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater)

        val firstFragment = ListFragment()
        sensorManager = view?.getContext()?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

        binding.txtCurrentTemp.text = temperature.toString()    // dit heb ik nog niet zien werken, °C moet er ook nog bij maar wou eerst dit testen
        binding.txtWeather.text = "Too cold for being outside"  // hiervoor dus nog een if/else schrijven

        binding.btnStart.setOnClickListener{
            (activity as MainActivity).switchTo(firstFragment)
            onPause()
        }

        return binding.root
    }


    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        val temperatureChanged = event.values[0]
        // Do something with this sensor data.
    }

    override fun onResume() {
        // Register a listener for the sensor.
        super.onResume()
        sensorManager.registerListener(this, temperature, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}