package com.example.dnd_list_2.model

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DataStorage {
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