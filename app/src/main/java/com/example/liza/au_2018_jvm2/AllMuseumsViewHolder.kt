package com.example.liza.au_2018_jvm2

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.firebase.client.Firebase
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.all_museums_view_holder.view.*

class AllMuseumsViewHolder(internal var mView: View) : RecyclerView.ViewHolder(mView), View.OnClickListener {

    private val FIREBASE_URL = "https://au-2018-jvm2.firebaseio.com"
    private var mFirebase: Firebase? = null

    internal var mContext: Context
    internal var mMuseum: Museum? = null

    init {
        mContext = mView.getContext()
        mView.setOnClickListener(this)
        mFirebase = Firebase(FIREBASE_URL)
    }

    fun bindMuseum(museum: Museum) {
        mMuseum = museum

        mView.museum_name_text_view.text = museum.name
        mView.museum_description_text_view.text = museum.description

        val cs = "Museum page bound"
        Toast.makeText(this.mContext, cs, Toast.LENGTH_LONG).show()
    }

    override fun onClick(view: View) {
//        val cs = "Museum page will be here"
//        Toast.makeText(this.mContext, cs, Toast.LENGTH_LONG).show()
        val alertDialogBuilder = AlertDialog.Builder(mContext)
        alertDialogBuilder.setTitle(mMuseum!!.name);
        alertDialogBuilder
                .setMessage(mMuseum!!.description)
                .setCancelable(false)
                .setPositiveButton("Add to my list!", { dialogInterface: DialogInterface, i: Int ->
                    // add to local storage
                    Toast.makeText(mContext, "Will add later...", Toast.LENGTH_LONG).show()
                })
                .setNegativeButton("Back",{ dialogInterface: DialogInterface, i: Int ->
                    // add to local storage
                    Toast.makeText(mContext, "Cancelling...", Toast.LENGTH_LONG).show()
                    dialogInterface.cancel();
                })
        val alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
