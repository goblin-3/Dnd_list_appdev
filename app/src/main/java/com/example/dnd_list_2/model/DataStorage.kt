package com.example.dnd_list_2.model

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DataStorage {
    fun saveListInPreferences(context: Context, list: kotlin.collections.List<com.example.dnd_list_2.model.List>, key: String) {
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

    /*val gson = Gson()

    private fun saveSpellList(list: String, sharedPreferences: SharedPreferences) {
        val toSaveSpellList = gson.toJson(list)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("spellList", toSaveSpellList)
        }.apply()
    }

    private fun saveItemList(list: String, sharedPreferences: SharedPreferences){
        val toSaveItemList = gson.toJson(list)
        val editors = sharedPreferences.edit()
        editors.apply{
            putString("itemList", toSaveItemList)
        }.apply()
    }

    fun saveSpell(sharedPreferences: SharedPreferences, sharedPreferencesList : SharedPreferences, dndList : MutableList<String>){
        var tempList = ""
        dndList.forEach() { List ->
            saveSpellList(List, sharedPreferences)
            tempList = tempList + List.toString() + "-"
        }
        saveSpellList(tempList, sharedPreferencesList)
    }

    fun saveItem(sharedPreferences: SharedPreferences, sharedPreferencesList : SharedPreferences, dndList : MutableList<String>){
        var tempList = ""
        dndList.forEach() { List ->
            saveItemList(List, sharedPreferences)
            tempList = tempList + List.toString() + "-"
        }
        saveItemList(tempList, sharedPreferencesList)
    }


    fun getDataSpellList(sharedPreferences: SharedPreferences, sharedPreferencesList : SharedPreferences, dndList : MutableList<List>){
        if(sharedPreferencesList.contains("spellList")) {
            val savedList = sharedPreferencesList.getString("spellList", null)
            var listString: kotlin.collections.List<String> = savedList!!.split("-")
            listString.forEach() {
                if (!(it == "") && sharedPreferences.contains(it)) {
                    val savedString = sharedPreferences.getString(it, null)
                    val title = gson.fromJson(savedString, List::class.java)
                    dndList.add(title)
                }
            }
        }
    }

    fun getDataItemList(sharedPreferences: SharedPreferences, sharedPreferencesList : SharedPreferences, dndList : MutableList<List>){
        if(sharedPreferencesList.contains("itemList")) {
            val savedList = sharedPreferencesList.getString("itemList", null)
            var listString: kotlin.collections.List<String> = savedList!!.split("-")
            listString.forEach() {
                if (!(it == "") && sharedPreferences.contains(it)) {
                    val savedString = sharedPreferences.getString(it, null)
                    val title = gson.fromJson(savedString, List::class.java)
                    dndList.add(title)
                }
            }
        }
    }

    fun deleteAll(sharedPreferences : SharedPreferences, sharedPreferencesList : SharedPreferences){
        val editor = sharedPreferences.edit()
        editor.apply{
            editor.clear()
        }.apply()

        val editors = sharedPreferencesList.edit()
        editors.apply{
            editors.clear()
        }.apply()
    }*/

}