package com.example.dnd_list_2


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dnd_list_2.databinding.FragmentStartBinding
import android.content.Context
import android.hardware.SensorManager

class StartFragment : Fragment(R.layout.fragment_start) {

    lateinit var binding: FragmentStartBinding
    private var listFragment = ListFragment()
    val sensors: MutableList<Sensable> = mutableListOf()
    val sensorManager: SensorManager
        get() = view?.getContext()?.getSystemService(Context.SENSOR_SERVICE) as SensorManager


    //
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater)

        val firstFragment = ListFragment()

        this::activateSensors

        binding.btnStart.setOnClickListener{
            (activity as MainActivity).switchTo(firstFragment)
            onPause()
        }

        tryToActivateSensor(TemperatureSensor(sensorManager))

        return binding.root
    }

    private fun tryToActivateSensor(sensor: Sensable) {
        if (!sensor.isSensable()) {
            binding.txtWeather.text = ("Whoops, no ${sensor::class.simpleName} sensor present?")
        return
        }
        sensor.also { it.sense()
            sensors.add(it)}
    }

    private fun activateSensors(view: View) {
        tryToActivateSensor(TemperatureSensor(sensorManager) {

            if (Integer.parseInt( it.toString() )<= 18 ) {
                binding.txtWeather.text = "Too cold for being outside, Just the excuse you needed to play Dnd indoors"
            } else {
                binding.txtWeather.text = "Just the right tempurature too play Dnd outdoors"        }
            binding.txtCurrentTemp.text ="${it.toString()}°c "
        })

    }

    override fun onPause() {
        super.onPause()
        sensors.forEach{it.pause()}
    }
}



