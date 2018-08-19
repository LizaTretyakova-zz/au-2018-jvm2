package com.example.liza.au2018jvm2

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.liza.au2018jvm2.Database.MuseumDBHelper
import com.firebase.client.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.all_museums_view_holder.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton

class AllMuseumsViewHolder(private var mView: View) : RecyclerView.ViewHolder(mView) {

    companion object {
        private const val FIREBASE_URL = "https://au-2018-jvm2.firebaseio.com"
        private var mFirebase: Firebase? = null
    }

    private val mContext: Context = mView.context
    private var mMuseum: Museum? = null

    init {
        mView.setOnClickListener {
            mContext.alert(mMuseum!!.description, mMuseum!!.name) {
                positiveButton("Add to my list!") { MuseumDBHelper(mContext).addMuseum(mMuseum!!)}
                noButton { it.cancel() }
            }.show()
        }
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
}
