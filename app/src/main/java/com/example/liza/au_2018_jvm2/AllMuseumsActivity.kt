package com.example.liza.au_2018_jvm2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.firebase.client.Firebase
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_all_museums.*

class AllMuseumsActivity : AppCompatActivity() {
    private val FIREBASE_URL = "https://au-2018-jvm2.firebaseio.com"
    private val FIREBASE_ROOT_NODE = "museums"

    private var mMuseumsReference: DatabaseReference? = null
    private var mFirebaseAdapter: FirebaseRecyclerAdapter<Museum, AllMuseumsViewHolder>? = null
    private var mFirebase: Firebase? = null
    private var mOptions: FirebaseRecyclerOptions<Museum>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_all_museums)

//        Firebase.setAndroidContext(this)
//        mFirebase = Firebase(FIREBASE_URL)
        mMuseumsReference = FirebaseDatabase.getInstance().getReference(FIREBASE_ROOT_NODE);
//        setUpFirebaseAdapter()

        val query = mMuseumsReference!!.child("descriptions")
        mOptions = FirebaseRecyclerOptions.Builder<Museum>()
                .setQuery(query, Museum::class.java)
                .build() // maybe  need customizable parser
        mFirebaseAdapter = object : FirebaseRecyclerAdapter<Museum, AllMuseumsViewHolder>(mOptions!!) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMuseumsViewHolder {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.all_museums_view_holder, parent, false)
                return AllMuseumsViewHolder(view)
            }

            override fun onBindViewHolder(holder: AllMuseumsViewHolder, position: Int, model: Museum) {
                holder.bindMuseum(model)
            }
        }
        all_museums!!.setHasFixedSize(true)
        all_museums!!.layoutManager = LinearLayoutManager(this)
        all_museums!!.adapter = mFirebaseAdapter
        Toast.makeText(this, "ONCREATE", Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "ONSTART", Toast.LENGTH_LONG).show()
        mFirebaseAdapter!!.startListening()

        // For the sake of testing, put one hard-coded ViewHolder here. Amen.
        Firebase.setAndroidContext(this)
        val view = LayoutInflater.from(this)
                .inflate(R.layout.all_museums_view_holder, all_museums, false)
        val holder = AllMuseumsViewHolder(view)
        holder.bindMuseum(Museum())
        if (view == null) {
            Toast.makeText(this, "VIEW IS NULL", Toast.LENGTH_LONG).show()
        } else if (all_museums == null) {
            Toast.makeText(this, "ALL_MUSEUMS IS NULL", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "WAT?!", Toast.LENGTH_LONG).show()
        }
//        all_museums!!.addView(view)
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "ONSTOP", Toast.LENGTH_LONG).show()
        mFirebaseAdapter!!.stopListening()
    }
}
