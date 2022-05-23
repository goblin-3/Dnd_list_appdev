package com.example.dnd_list_2.model

import android.content.SharedPreferences
import com.google.gson.Gson

class DataStorage {

    val gson = Gson()
    var DndList = mutableListOf<List>()

    private fun saveList(list: List, sharedPreferences: SharedPreferences) {
        val ToSaveList = gson.toJson(list)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString(list.title, ToSaveList)
        }.apply()
    }

    private fun saveList(list: String, sharedPreferences: SharedPreferences){
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("Listname", list)
        }.apply()
    }

    fun save(sharedPreferences: SharedPreferences, sharedPreferenceslist : SharedPreferences, DndList : MutableList<List>){
        var tempList = ""
        DndList.forEach() { List ->
            saveList(List, sharedPreferences)
            tempList = tempList + List.title + "/"
        }
        saveList(tempList, sharedPreferenceslist)
    }


    fun getData(sharedPreferences: SharedPreferences,  sharedPreferencesList : SharedPreferences, DndList : MutableList<List>){
        if(sharedPreferencesList.contains("Listname")) {
            val savedList = sharedPreferencesList.getString("Listname", null)
            var TitleString: kotlin.collections.List<String> = savedList!!.split("/")
            TitleString.forEach() {
                if (!(it == "")&&sharedPreferences.contains(it)) {
                    val savedString = sharedPreferences.getString(it, null)
                    val title = gson.fromJson(savedString, List::class.java)
                    DndList.add(title)
                }
            }
        }
    }
    fun delete(sharedPreferences: SharedPreferences,  sharedPreferencesList : SharedPreferences){
        val editor = sharedPreferences.edit()
        editor.apply{
            editor.clear()
        }.apply()

        val editors = sharedPreferencesList.edit()
        editors.apply{
            editors.clear()
        }.apply()
    }

}