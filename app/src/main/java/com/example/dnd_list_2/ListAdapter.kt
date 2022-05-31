package com.example.dnd_list_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter (val items:List<com.example.dnd_list_2.model.List>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    inner class ListViewHolder(currentItemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(currentItemView) {
        init {
            currentItemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentListItem = items[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.txtListTitle).text = currentListItem.title
        }
    }


    override fun getItemCount(): Int = items.size

    /*fun getItem(position: Int): Any{
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }*/
    /*
    override fun getView(position: Int, convertView: View?,parent: ViewGroup):
            View{
        val rowView = inflater.inflate(R.layout.list_item_items, parent, false)
        return rowView
    }*/
}