package com.example.dnd_list_2.model

import android.content.SharedPreferences
import com.google.gson.Gson

class DataStorage {

    val gson = Gson()

    private fun saveFirstList(list: String, sharedPreferences: SharedPreferences) {
        val toSaveFirstList = gson.toJson(list)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("FirstListName", toSaveFirstList)
        }.apply()
    }

    private fun saveSecondList(list: String, sharedPreferences: SharedPreferences){
        val toSaveSecondList = gson.toJson(list)
        val editors = sharedPreferences.edit()
        editors.apply{
            putString("SecondListName", toSaveSecondList)
        }.apply()
    }

    fun saveFirst(sharedPreferences: SharedPreferences, sharedPreferencesList : SharedPreferences, DndList : MutableList<String>){
        var tempList = ""
        DndList.forEach() { List ->
            saveFirstList(List, sharedPreferences)
            tempList = tempList + String + "-"
        }
        saveFirstList(tempList, sharedPreferencesList)
    }

    fun saveSecond(sharedPreferences: SharedPreferences, sharedPreferencesList : SharedPreferences, DndList2 : MutableList<String>){
        var tempList = ""
        DndList2.forEach() { List ->
            saveSecondList(List, sharedPreferences)
            tempList = tempList + String + "-"
        }
        saveSecondList(tempList, sharedPreferencesList)
    }


    fun getDataFirstList(sharedPreferences: SharedPreferences, sharedPreferencesList : SharedPreferences, DndList : MutableList<List>){
        if(sharedPreferencesList.contains("FirstListName")) {
            val savedList = sharedPreferencesList.getString("FirstListName", null)
            var titleString: kotlin.collections.List<String> = savedList!!.split("-")
            titleString.forEach() {
                if (!(it == "")&&sharedPreferences.contains(it)) {
                    val savedString = sharedPreferences.getString(it, null)
                    val title = gson.fromJson(savedString, List::class.java)
                    DndList.add(title)
                }
            }
        }
    }

    fun getDataSecondList(sharedPreferences: SharedPreferences, sharedPreferencesList : SharedPreferences, DndList : MutableList<List>){
        if(sharedPreferencesList.contains("SecondListName")) {
            val savedList = sharedPreferencesList.getString("SecondListName", null)
            var titleString: kotlin.collections.List<String> = savedList!!.split("-")
            titleString.forEach() {
                if (!(it == "")&&sharedPreferences.contains(it)) {
                    val savedString = sharedPreferences.getString(it, null)
                    val title = gson.fromJson(savedString, List::class.java)
                    DndList.add(title)
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
    }

}