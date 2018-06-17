package com.example.liza.au_2018_jvm2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.SimpleCursorAdapter
import android.widget.TwoLineListItem
import com.example.liza.au_2018_jvm2.Database.MuseumDBHelper
import com.example.liza.au_2018_jvm2.Database.MuseumDBHelper.Companion.COLUMN_DESCRIPTION
import com.example.liza.au_2018_jvm2.Database.MuseumDBHelper.Companion.COLUMN_NAME
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onItemClick

class MyMuseumsActivity : AppCompatActivity() {

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

        linearLayout {

            listView {
                adapter = mAdapter

                onItemClick { _: AdapterView<*>?, v: View?, _: Int, _: Long ->
                    alert("Delete museum " + (v as TwoLineListItem).text1.text + " from your list?",
                            "Delete item") {
                        yesButton {
                            val dbHelper = MuseumDBHelper(ctx)
                            dbHelper.deleteMuseum(v.text1.text.toString())
                            mAdapter.changeCursor(dbHelper.getAllMuseumsCursor())
                        }
                        noButton { it.cancel() }
                    }.show()
                }
            }
        }
    }
}
