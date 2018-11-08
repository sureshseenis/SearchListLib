package com.arivista.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arivista.myapplication.Model.SearchItem
import com.arivista.myapplication.R

import java.util.ArrayList

class SearchRecyclerview(internal var context: Context, d: ArrayList<SearchItem>) : RecyclerView.Adapter<SearchRecyclerview.SearchViewHolder>() {

    internal var data = ArrayList<SearchItem>()
    internal var inflater: LayoutInflater

    init {
        this.data = d
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(inflater.inflate(R.layout.searchlistview, parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.search_txt.text = data[position].searchText
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var search_txt: TextView

        init {
            search_txt = itemView.findViewById(R.id.search_txt) as TextView
        }
    }

}