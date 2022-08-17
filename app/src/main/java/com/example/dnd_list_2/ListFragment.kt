package com.example.dnd_list_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dnd_list_2.databinding.FragmentListBinding
import com.example.dnd_list_2.model.DataStorage
import com.example.dnd_list_2.model.List


class ListFragment: Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding

    private lateinit var main: MainActivity
    private lateinit var adapter: ListAdapter
    private val storage = DataStorage()
    private val user =LoginFragment()
    var currentList = ArrayList<List>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        main = activity as MainActivity

        if (currentFrame == 1) {
            currentList = getContext()?.let { storage.readListFromPreferences(it, "spell" + "${sMail}") } as ArrayList<List>
        } else if (currentFrame == 2) {
            currentList = getContext()?.let { storage.readListFromPreferences(it, "item"+ "${sMail}") } as ArrayList<List>
        }

        adapter = ListAdapter(currentList)
        binding.rvwList.layoutManager = LinearLayoutManager(this.context)
        binding.rvwList.adapter = adapter

        binding.btnAddList.setOnClickListener {
            val newListTitle = binding.edtList.text.toString()
            currentList.add(List(newListTitle))
            adapter.notifyItemInserted(currentList.size - 1)
            //adapter.notifyDataSetChanged()

            if (currentFrame == 1) {
                getContext()?.let { it1 -> storage.saveListInPreferences(it1, currentList, "spell"+ "${sMail}") }
            } else if (currentFrame == 2) {
                getContext()?.let { it1 -> storage.saveListInPreferences(it1, currentList, "item"+ "${sMail}") }
            }

            binding.edtList.text.clear()
            binding.edtList.clearFocus()
            main.hideKeyboard(it)
        }
        return binding.root
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


    fun clearAllItems() {
        if (currentFrame == 1) {
            currentList = getContext()?.let { storage.readListFromPreferences(it, "spell"+ "${sMail}") } as ArrayList<List>
            if (currentList.size >= 1) {
                currentList.clear()
                adapter.notifyDataSetChanged()
                getContext()?.let { it1 -> storage.saveListInPreferences(it1, currentList, "spell"+ "${sMail}") }
            }
        }
        if (currentFrame == 2) {
            currentList = getContext()?.let { storage.readListFromPreferences(it, "item"+ "${sMail}") } as ArrayList<List>
            if (currentList.size >= 1) {
                currentList.clear()
                adapter.notifyDataSetChanged()
                getContext()?.let { it1 -> storage.saveListInPreferences(it1, currentList, "item"+ "${sMail}") }
            }
        }
    }

    fun clearLatestItem() {
        if (currentFrame == 1) {
            currentList = getContext()?.let { storage.readListFromPreferences(it, "spell"+ "${sMail}") } as ArrayList<List>
            if (currentList.size >= 1) {
                currentList.removeAt(currentList.size - 1)
                //adapter.notifyDataSetChanged()
                adapter.notifyItemRemoved(currentList.size - 1)
                getContext()?.let { it1 -> storage.saveListInPreferences(it1, currentList, "spell"+ "${sMail}") }
            }
        }
        if (currentFrame == 2) {
            currentList = getContext()?.let { storage.readListFromPreferences(it, "item"+ "${sMail}") } as ArrayList<List>
            if (currentList.size >= 1) {
                currentList.removeAt(currentList.size - 1)
                //adapter.notifyDataSetChanged()
                adapter.notifyItemRemoved(currentList.size)
                getContext()?.let { it1 -> storage.saveListInPreferences(it1, currentList, "item"+ "${sMail}") }
            }
        }
    }

    fun resetItems() {
        if (currentFrame == 1) {
            currentList = getContext()?.let { storage.readListFromPreferences(it, "spell"+ "${sMail}") } as ArrayList<List>
            currentList.clear()
            currentList.addAll(spellList())
            adapter.notifyDataSetChanged()
            getContext()?.let { it1 -> storage.saveListInPreferences(it1, currentList, "spell"+ "${sMail}") }
        }
        if (currentFrame == 2) {
            currentList = getContext()?.let { storage.readListFromPreferences(it, "item"+ "${sMail}") } as ArrayList<List>
            currentList.clear()
            currentList.addAll(itemList())
            adapter.notifyDataSetChanged()
            getContext()?.let { it1 -> storage.saveListInPreferences(it1, currentList, "item"+ "${sMail}") }
        }
    }

}