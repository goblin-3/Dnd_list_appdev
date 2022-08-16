package com.example.dnd_list_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dnd_list_2.databinding.InlogVensterBinding

class LoginFragment : Fragment(R.layout.inlog_venster) {

    private lateinit var binding:InlogVensterBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InlogVensterBinding.inflate(layoutInflater)





        return binding.root

    }



}