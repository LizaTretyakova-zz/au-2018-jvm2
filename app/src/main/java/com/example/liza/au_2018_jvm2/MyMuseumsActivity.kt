package com.example.liza.au_2018_jvm2

import android.app.ListActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import com.example.liza.au_2018_jvm2.Database.MuseumDBHelper
import com.example.liza.au_2018_jvm2.Database.MuseumDBHelper.Companion.COLUMN_DESCRIPTION
import com.example.liza.au_2018_jvm2.Database.MuseumDBHelper.Companion.COLUMN_NAME

// These are the Contacts rows that we will retrieve
internal val PROJECTION = arrayOf(COLUMN_NAME, COLUMN_DESCRIPTION)

// This is the select criteria
internal const val SELECTION = "((" + BaseColumns._ID + " NOTNULL) AND (" +
        BaseColumns._ID + " != '' ))"

class MyMuseumsActivity : ListActivity() {

    // This is the Adapter being used to display the list's data
    private lateinit var mAdapter: SimpleCursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // For the cursor adapter, specify which columns go into which views
        val fromColumns: Array<String> = arrayOf(COLUMN_NAME, COLUMN_DESCRIPTION)
        val toViews = intArrayOf(android.R.id.text1, android.R.id.text2) // The TextView in simple_list_item_2

        val dbHelper = MuseumDBHelper(this, null, null)
        mAdapter = SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, dbHelper.getAllMuseumsCursor(),
                fromColumns, toViews, 0)
        listAdapter = mAdapter
    }


    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        // Do something when a list item is clicked
    }
}
