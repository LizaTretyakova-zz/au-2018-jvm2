package com.example.liza.au_2018_jvm2

import android.app.ListActivity
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TwoLineListItem
import com.example.liza.au_2018_jvm2.Database.MuseumDBHelper
import com.example.liza.au_2018_jvm2.Database.MuseumDBHelper.Companion.COLUMN_DESCRIPTION
import com.example.liza.au_2018_jvm2.Database.MuseumDBHelper.Companion.COLUMN_NAME

class MyMuseumsActivity : ListActivity() {

    // This is the Adapter being used to display the list's data
    private lateinit var mAdapter: SimpleCursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // For the cursor adapter, specify which columns go into which views
        val fromColumns: Array<String> = arrayOf(COLUMN_NAME, COLUMN_DESCRIPTION)
        val toViews = intArrayOf(android.R.id.text1, android.R.id.text2) // The TextView's in simple_list_item_2

        val dbHelper = MuseumDBHelper(this)
        mAdapter = SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, dbHelper.getAllMuseumsCursor(),
                fromColumns, toViews, 0)
        listAdapter = mAdapter
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Delete item")
        alertDialogBuilder
                .setMessage("Delete museum " + (v as TwoLineListItem).text1.text + " from your list?")
                .setCancelable(false)
                .setPositiveButton("Delete") { _: DialogInterface, _: Int ->
                    // add to local storage
                    val dbHelper = MuseumDBHelper(this)
                    dbHelper.deleteMuseum(v.text1.text.toString())
                    mAdapter.changeCursor(dbHelper.getAllMuseumsCursor())
                }
                .setNegativeButton("Back") { dialogInterface: DialogInterface, _: Int ->
                    // add to local storage
                    dialogInterface.cancel()
                }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
