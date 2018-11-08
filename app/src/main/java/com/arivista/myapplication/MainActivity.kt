package com.arivista.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.arivista.myapplication.Model.SearchItem
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var searchItems: ArrayList<SearchItem>?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchItems = ArrayList()
        searchItems!!.add(SearchItem(1, "Hai"))
        searchItems!!.add(SearchItem(2, "Suresh"))


        val getSearchItems = findViewById<View>(R.id.itemsBtn) as Button
        getSearchItems.setOnClickListener { SearchBox(this).ShowSearchBox(searchItems!!) }
    }
}
