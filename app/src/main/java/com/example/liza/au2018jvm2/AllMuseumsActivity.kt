package com.example.liza.au2018jvm2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

class AllMuseumsActivity : AppCompatActivity() {

    companion object {
        private const val FIREBASE_ROOT_NODE = "descriptions2"
    }

    private lateinit var mFirebaseAdapter: FirebaseRecyclerAdapter<Museum, AllMuseumsViewHolder>
    private lateinit var mAllMuseumsView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        constraintLayout {
            mAllMuseumsView = recyclerView {
                id = Ids.all_museums
            }.lparams(width = matchParent, height = matchParent)
        }

        val mMuseumsReference = FirebaseDatabase.getInstance().getReference(FIREBASE_ROOT_NODE)
        val mOptions = FirebaseRecyclerOptions.Builder<Museum>()
                .setQuery(mMuseumsReference, Museum::class.java)
                .build()
        mFirebaseAdapter = object : FirebaseRecyclerAdapter<Museum, AllMuseumsViewHolder>(mOptions) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMuseumsViewHolder {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.all_museums_view_holder, parent, false)
                return AllMuseumsViewHolder(view)
            }

            override fun onBindViewHolder(holder: AllMuseumsViewHolder, position: Int, model: Museum) {
                holder.bindMuseum(model)
            }
        }
        mAllMuseumsView.setHasFixedSize(true)
        mAllMuseumsView.layoutManager = LinearLayoutManager(this)
        mAllMuseumsView.adapter = mFirebaseAdapter
    }

    override fun onStart() {
        super.onStart()
        mFirebaseAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mFirebaseAdapter.stopListening()
    }

    private object Ids {
        const val all_museums = 1
    }
}
