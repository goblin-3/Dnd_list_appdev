package com.example.dnd_list_2

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dnd_list_2.databinding.FragmentList1Binding
import com.example.dnd_list_2.model.List


class ListFragment: Fragment(R.layout.fragment_list1) {

    private lateinit var binding: FragmentList1Binding
    private val List_example = sampleList()
    private lateinit var main: MainActivity
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentList1Binding.inflate(layoutInflater)
        main = activity as MainActivity

        adapter = ListAdapter(List_example)
        binding.rvwList.adapter = adapter

        binding.rvwList.layoutManager = LinearLayoutManager(this.context)

        val sharedPref = activity?.getSharedPreferences(
            "Dnd_list_appdev", Context.MODE_PRIVATE)

        binding.btnAddList.setOnClickListener {
            val newListTitle = binding.edtList.text.toString()
            List_example.add(List(newListTitle))
            adapter.notifyItemInserted(List_example.size - 1)



            binding.edtList.text.clear()
            binding.edtList.clearFocus()
            main.hideKeyboard(it)
        }
        return binding.root

    }

    fun clearAllItems(){
        List_example.clear()
        adapter.notifyDataSetChanged()
    }

    fun clearLatestItem(){
        if(List_example.size>=1){
            List_example.removeAt(List_example.size-1)
            adapter.notifyItemRemoved(List_example.size-1)
        }
    }

    fun resetItems(){
        List_example.clear()
        List_example.addAll(sampleList())
        adapter.notifyDataSetChanged()
    }

    fun linkToWebsite() {
        val url = "http://dnd5e.wikidot.com"
        val browse = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browse)
    }

    private fun sampleList() = arrayListOf(
        List("evocation"),
        List("conjuration"),
        List("necromancy"),
        List("The_only_spell"),
    )
}