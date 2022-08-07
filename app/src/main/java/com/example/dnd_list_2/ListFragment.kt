package com.example.dnd_list_2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dnd_list_2.databinding.FragmentList1Binding
import com.example.dnd_list_2.model.List
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListFragment: Fragment(R.layout.fragment_list1) {

    private lateinit var binding: FragmentList1Binding

    private lateinit var main: MainActivity
    private lateinit var adapter: ListAdapter
    // lateinit var list1: SharedPreferences
    // lateinit var list2: SharedPreferences
    //  val memory : DataStorage = DataStorage()
    var currentList = ArrayList<List>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentList1Binding.inflate(layoutInflater)
        main = activity as MainActivity

        //val sharedPref = activity?.getSharedPreferences("Dnd_list_appdev", Context.MODE_PRIVATE)!!

        if (currentFrame == 1) {
            currentList = getContext()?.let { readListFromPreferences(it, "spell") } as ArrayList<List>
            //listExample = spellList()
            //memory.getDataFirstList(sharedPref,list1,listExample)
        } else {
            //memory.getDataSecondList(sharedPref,list2,listExample)
            currentList = getContext()?.let { readListFromPreferences(it, "item") } as ArrayList<List>
        }

        adapter = ListAdapter(currentList)
        binding.rvwList.layoutManager = LinearLayoutManager(this.context)
        binding.rvwList.adapter = adapter

        //val sharedPref = activity?.getSharedPreferences("Dnd_list_appdev", Context.MODE_PRIVATE)

        binding.btnAddList.setOnClickListener {
            val newListTitle = binding.edtList.text.toString()
            currentList.add(List(newListTitle))
            adapter.notifyItemInserted(currentList.size - 1)

            if (currentFrame == 1) {
                getContext()?.let { it1 -> saveListInPreferences(it1, currentList, "spell")
            }} else {
                getContext()?.let { it1 -> saveListInPreferences(it1, currentList, "item")
            }}

            binding.edtList.text.clear()
            binding.edtList.clearFocus()
            main.hideKeyboard(it)
        }
        return binding.root
    }


    fun clearAllItems() {
        if (currentFrame == 1) {
            currentList = getContext()?.let { readListFromPreferences(it, "spell") } as ArrayList<List>
            currentList.clear()
            getContext()?.let { it1 -> saveListInPreferences(it1, currentList, "spell")
            adapter.notifyDataSetChanged()
        }}
        if (currentFrame == 2) {
            currentList = getContext()?.let { readListFromPreferences(it, "item") } as ArrayList<List>
            currentList.clear()
            getContext()?.let { it1 -> saveListInPreferences(it1, currentList, "item")
            adapter.notifyDataSetChanged()
        }}
    }

    fun clearLatestItem() {
        if (currentList.size >= 1) {
            currentList.removeAt(currentList.size - 1)
            adapter.notifyItemRemoved(currentList.size - 1)
        }
    }

    fun resetItems() {
        currentList.clear()
        currentList.addAll(spellList())
        adapter.notifyDataSetChanged()
    }


    private fun spellList() = arrayListOf(
        List("Evocation"),
        List("Conjuration"),
        List("Necromancy"),
        List("The only spell"),
    )

    private fun itemList() = arrayListOf(
        List("Axe"),
        List("GreatSword"),
        List("Wand of necroDancy"),
        List("40 ft Rope"),
    )

    fun saveListInPreferences(context: Context, list: kotlin.collections.List<List>, key: String) {
        val gson = Gson()
        val jsonString = gson.toJson(list)

        val sharedPreferences : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, jsonString)
        editor.apply()
    }

    fun readListFromPreferences(context: Context, key : String): ArrayList<List> {
        val sharedPreferences : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val jsonString = sharedPreferences.getString(key, emptyList<List>().toString())

        val gson = Gson()
        val type = object : TypeToken<ArrayList<List>>() {}.type
        val list : ArrayList<List> = gson.fromJson(jsonString, type)
        return list
    }

}