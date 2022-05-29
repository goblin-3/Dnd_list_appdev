package com.example.dnd_list_2

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dnd_list_2.databinding.FragmentStartBinding

class StartFragment : Fragment(R.layout.fragment_start) {

    private lateinit var binding: FragmentStartBinding
    private var listFragment = ListFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater)

        val firstFragment = ListFragment()
        binding.txtCurrentTemp.setText("21°C")
        binding.txtWeather.setText("Too cold for being outside")
        binding.btnStart.setOnClickListener{ (activity as MainActivity).switchTo(firstFragment) }

        return binding.root
    }


}