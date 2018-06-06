package com.example.liza.au_2018_jvm2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.firebase.client.Firebase
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_all_museums.*

class AllMuseumsActivity : AppCompatActivity() {
    private val FIREBASE_URL = "https://au-2018-jvm2.firebaseio.com"
    private val FIREBASE_ROOT_NODE = "museums"

    private var mMuseumsReference: DatabaseReference? = null
    private var mFirebaseAdapter: FirebaseRecyclerAdapter<Museum, AllMuseumsViewHolder>? = null
    private var mFirebase: Firebase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_all_museums)

//        Firebase.setAndroidContext(this)
//        mFirebase = Firebase(FIREBASE_URL)
        mMuseumsReference = FirebaseDatabase.getInstance().getReference(FIREBASE_ROOT_NODE);
        setUpFirebaseAdapter()
    }

    private fun setUpFirebaseAdapter() {
        mFirebaseAdapter = object : FirebaseRecyclerAdapter<Museum, AllMuseumsViewHolder>(
                Museum::class.java, R.layout.all_museums_view_holder,
                AllMuseumsViewHolder::class.java, mMuseumsReference) {

            override fun populateViewHolder(viewHolder: AllMuseumsViewHolder,
                                                      model: Museum, position: Int) {
                viewHolder.bindMuseum(model)
            }
        }
        all_museums!!.setHasFixedSize(true)
        all_museums!!.setLayoutManager(LinearLayoutManager(this))
        all_museums!!.setAdapter(mFirebaseAdapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        mFirebaseAdapter!!.cleanup()
    }
}
