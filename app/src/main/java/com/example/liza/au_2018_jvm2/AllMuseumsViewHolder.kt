package com.example.liza.au_2018_jvm2

import android.content.Context
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

    init {
        mContext = mView.getContext()
        mView.setOnClickListener(this)
        mFirebase = Firebase(FIREBASE_URL)
    }

    fun bindMuseum(museum: Museum) {
//
//        Picasso.with(mContext)
//                .load(restaurant.getImageUrl())
//                .resize(MAX_WIDTH, MAX_HEIGHT)
//                .centerCrop()
//                .into(restaurantImageView)

        mView.museum_name_text_view.text = museum.name
        mView.museum_description_text_view.text = museum.description
    }

    override fun onClick(view: View) {
        val cs = "Museum page will be here"
        Toast.makeText(this.mContext, cs, Toast.LENGTH_LONG).show()
//        val restaurants = ArrayList<Museum>()
//        val ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RESTAURANTS)
//        ref.addListenerForSingleValueEvent(object : ValueEventListener() {
//
//            fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (snapshot in dataSnapshot.getChildren()) {
//                    restaurants.add(snapshot.getValue(Restaurant::class.java))
//                }
//
//                val itemPosition = getLayoutPosition()
//
//                val intent = Intent(mContext, RestaurantDetailActivity::class.java)
//                intent.putExtra("position", itemPosition + "")
//                intent.putExtra("restaurants", Parcels.wrap(restaurants))
//
//                mContext.startActivity(intent)
//            }
//
//            fun onCancelled(databaseError: DatabaseError) {}
//        })
    }

//    companion object {
//        private val MAX_WIDTH = 200
//        private val MAX_HEIGHT = 200
//    }
}
