package com.example.dnd_list_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dnd_list_2.databinding.FragmentList2Binding
import com.example.dnd_list_2.model.List

class ListFragment2:Fragment(R.layout.fragment_list2) {

    private lateinit var binding: FragmentList2Binding
    private lateinit var adapter:ListAdapter
    private lateinit var main: MainActivity
    private val List2_example = sampleList2()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentList2Binding.inflate(layoutInflater)
        main = activity as MainActivity

        adapter = ListAdapter(List2_example)
        binding.rvwList.adapter = adapter
        binding.rvwList.layoutManager = LinearLayoutManager(this.context)

        /*val sharedPref = activity?.getSharedPreferences(
            "Dnd_list_appdev", Context.MODE_PRIVATE)*/

        binding.btnAddList.setOnClickListener {
            val newListTitle = binding.edtList.text.toString()
            List2_example.add(List(newListTitle))
            adapter.notifyItemInserted(List2_example.size - 1)

            binding.edtList.text.clear()
            binding.edtList.clearFocus()
            main.hideKeyboard(it)
        }
        return binding.root
    }

    private fun sampleList2() = arrayListOf(
        List("Axe"),
        List("GreatSword"),
        List("Wand of necroDancy"),
        List("40 ft Rope"),
    )
}