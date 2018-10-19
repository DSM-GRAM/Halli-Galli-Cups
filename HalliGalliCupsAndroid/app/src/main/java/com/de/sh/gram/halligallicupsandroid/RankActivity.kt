package com.de.sh.gram.halligallicupsandroid

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout.VERTICAL
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_rank.*

class RankActivity : Activity() {

    lateinit var rankRecyclerAdapter: RankRecyclerAdapter
    lateinit var rankRecyclerHolder: RankRecyclerHolder
    lateinit var rankRecyclerItems: ArrayList<RankRecyclerItem>
    lateinit var rankLinearLayoutManager: LinearLayoutManager
    var score: Int = 0

    val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference: DatabaseReference = firebaseDatabase.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)

        var intent: Intent = getIntent()
        score = intent.getIntExtra("score",0)

        rankLinearLayoutManager = LinearLayoutManager(applicationContext)
        rankLinearLayoutManager.orientation= VERTICAL
        rankRecyclerItems = ArrayList<RankRecyclerItem>()
        recycler_rank.layoutManager = rankLinearLayoutManager

        rankRecyclerItems.add(RankRecyclerItem(1.toString(), "daeun", 23432.toString()))

        rankRecyclerAdapter = RankRecyclerAdapter(rankRecyclerItems)
        recycler_rank.adapter = rankRecyclerAdapter

    }

}
