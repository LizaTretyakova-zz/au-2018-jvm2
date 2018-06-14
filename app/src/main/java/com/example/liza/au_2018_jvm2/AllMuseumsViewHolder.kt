package com.example.liza.au_2018_jvm2

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.liza.au_2018_jvm2.Database.MuseumDBHelper
import com.firebase.client.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.all_museums_view_holder.view.*

class AllMuseumsViewHolder(internal var mView: View) : RecyclerView.ViewHolder(mView), View.OnClickListener {

    private val FIREBASE_URL = "https://au-2018-jvm2.firebaseio.com"
    private var mFirebase: Firebase? = null

    internal var mContext: Context
    internal var mMuseum: Museum? = null

    init {
        mContext = mView.getContext()
        mView.setOnClickListener(this)
        Firebase.setAndroidContext(mContext)
        mFirebase = Firebase(FIREBASE_URL)
    }

    fun bindMuseum(museum: Museum) {
        mMuseum = museum

        mView.museum_name_text_view.text = museum.name
        mView.museum_description_text_view.text = museum.description
        Picasso.get()
                .load(museum.url)
                .resize(300, 300)
                .centerCrop()
                .into(mView.museumImageView)
    }

    override fun onClick(view: View) {
        val alertDialogBuilder = AlertDialog.Builder(mContext)
        alertDialogBuilder.setTitle(mMuseum!!.name);
        alertDialogBuilder
                .setMessage(mMuseum!!.description)
                .setCancelable(false)
                .setPositiveButton("Add to my list!", { dialogInterface: DialogInterface, i: Int ->
                    // add to local storage
                    val dbHelper = MuseumDBHelper(mContext, null, null)
                    dbHelper.addMuseum(mMuseum!!)
                })
                .setNegativeButton("Back",{ dialogInterface: DialogInterface, i: Int ->
                    // add to local storage
                    dialogInterface.cancel();
                })
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
