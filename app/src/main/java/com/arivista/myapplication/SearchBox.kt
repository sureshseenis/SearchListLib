package com.arivista.myapplication

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast

import java.util.ArrayList

import android.content.Context.LAYOUT_INFLATER_SERVICE
import com.arivista.myapplication.Model.SearchItem
import com.arivista.myapplication.adapter.SearchRecyclerview

class SearchBox(var appcontext: Context) {
    internal var selectedSearchItem: SearchItem? = null
    internal var searchadapter: SearchRecyclerview? = null
    private var searchItems = ArrayList<SearchItem>()
    private var editText: EditText? = null
    internal var filteredList = ArrayList<SearchItem>()

    fun ShowSearchBox(items: ArrayList<SearchItem>) {
        ShowSearchBox(items, "")
    }

    fun ShowSearchBox(Items: ArrayList<SearchItem>, initText: String) {
        selectedSearchItem = null
        searchItems = Items
        searchadapter = SearchRecyclerview(appcontext, filteredList)

        val dialogBuilder = AlertDialog.Builder(appcontext)

        val inflater = appcontext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.searchlayout, null)
        dialogBuilder.setView(dialogView)

        editText = dialogView.findViewById(R.id.search_box)
        editText!!.setText(initText)
        editText!!.hint = "Search List"
        editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })

        val recyclerView = dialogView.findViewById(R.id.search_list) as RecyclerView
        recyclerView.adapter = searchadapter
        recyclerView.layoutManager = LinearLayoutManager(appcontext)
        filter(initText)
        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(appcontext, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                if (filteredList.size > 0) {
                    selectedSearchItem = filteredList[position]
                    editText!!.setText(selectedSearchItem!!.searchText)
                    Toast.makeText(appcontext, "" + selectedSearchItem!!.searchText, Toast.LENGTH_SHORT).show()
                } else {
                    selectedSearchItem = null
                }

            }
        }))
        dialogBuilder.setCancelable(true)
        dialogBuilder.setPositiveButton("Ok") { dialog, which ->
            //GetSearchResult(selectedSearchItem);
            dialog.dismiss()
        }
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun filter(text: String) {

        if (text.equals("", ignoreCase = true)) {
            filteredList.clear()
            Toast.makeText(appcontext, "Blank", Toast.LENGTH_SHORT).show()
            for (item in searchItems) {
                filteredList.add(item)
            }
        } else {
            filteredList.clear()
            for (item in searchItems) {
                if (item.searchText!!.toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item)
                }
            }
        }

        searchadapter!!.notifyDataSetChanged()
    }

}