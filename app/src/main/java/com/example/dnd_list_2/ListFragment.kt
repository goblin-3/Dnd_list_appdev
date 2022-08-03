package com.example.dnd_list_2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dnd_list_2.databinding.FragmentList1Binding
import com.example.dnd_list_2.model.DataStorage
import com.example.dnd_list_2.model.List


class ListFragment: Fragment(R.layout.fragment_list1) {

    private lateinit var binding: FragmentList1Binding
    var listExample = sampleList()
    private lateinit var main: MainActivity
    private lateinit var adapter: ListAdapter
    lateinit var list1: SharedPreferences
    lateinit var list2: SharedPreferences
    val memory : DataStorage = DataStorage()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentList1Binding.inflate(layoutInflater)
        main = activity as MainActivity


        val sharedPref = activity?.getSharedPreferences(
            "Dnd_list_appdev", Context.MODE_PRIVATE)!!

        if (currentFrame ==1){
            listExample = sampleList()
   //         memory.getDataFirstList(sharedPref,list1,listExample)
        }
        else{
     //       memory.getDataSecondList(sharedPref,list2,listExample)
            listExample = sampleList2()
        }

        adapter = ListAdapter(listExample)
        binding.rvwList.layoutManager = LinearLayoutManager(this.context)
        binding.rvwList.adapter = adapter



        binding.btnAddList.setOnClickListener {
            val newListTitle = binding.edtList.text.toString()
            listExample.add(List(newListTitle))
            adapter.notifyItemInserted(listExample.size - 1)

            binding.edtList.text.clear()
            binding.edtList.clearFocus()
            main.hideKeyboard(it)
        }
        return binding.root
    }

    fun clearAllItems(){
        listExample.clear()
        adapter.notifyDataSetChanged()
    }

    fun clearLatestItem(){
        if(listExample.size>=1){
            listExample.removeAt(listExample.size-1)
            adapter.notifyItemRemoved(listExample.size-1)
        }
    }

    fun resetItems(){
        listExample.clear()
        listExample.addAll(sampleList())
        adapter.notifyDataSetChanged()
    }

    private fun sampleList() = arrayListOf(
        List("Evocation"),
        List("Conjuration"),
        List("Necromancy"),
        List("The only spell"),
    )
    private fun sampleList2() = arrayListOf(
        List("Axe"),
        List("GreatSword"),
        List("Wand of necroDancy"),
        List("40 ft Rope"),
    )
}