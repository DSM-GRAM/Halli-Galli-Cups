package com.de.sh.gram.halligallicupsandroid

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout.VERTICAL
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_rank.*

class RankActivity : Activity() {

    lateinit var rankRecyclerAdapter: RankRecyclerAdapter
    lateinit var rankRecyclerHolder: RankRecyclerHolder
    lateinit var rankRecyclerItems: ArrayList<RankRecyclerItem>
    lateinit var rankLinearLayoutManager: LinearLayoutManager
    lateinit var firstRankData: RankRecyclerItem
    lateinit var secondRankData: RankRecyclerItem
    lateinit var thirdRankData: RankRecyclerItem
    lateinit var fourthRankData: RankRecyclerItem
    lateinit var fifthRankData: RankRecyclerItem


    var size: Int = 0
    var score: Int = 0

    val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference: DatabaseReference = firebaseDatabase.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)

        var intent: Intent = getIntent()
        score = intent.getIntExtra("score", 200)
        text_rank_main_score.text = score.toString()

        rankLinearLayoutManager = LinearLayoutManager(applicationContext)
        rankLinearLayoutManager.orientation = VERTICAL
        rankRecyclerItems = ArrayList<RankRecyclerItem>()
        rankRecyclerItems.add(0, RankRecyclerItem("1","없음","0"))
        rankRecyclerItems.add(1, RankRecyclerItem("1","없음","0"))
        rankRecyclerItems.add(2, RankRecyclerItem("1","없음","0"))
        rankRecyclerItems.add(3, RankRecyclerItem("1","없음","0"))
        rankRecyclerItems.add(4, RankRecyclerItem("1","없음","0"))
        recycler_rank.layoutManager = rankLinearLayoutManager

        rankRecyclerAdapter = RankRecyclerAdapter(rankRecyclerItems)
        recycler_rank.adapter = rankRecyclerAdapter

//        databaseReference.child("1").push().setValue(RankRecyclerItem("1","없음","0"))
//        databaseReference.child("2").push().setValue(RankRecyclerItem("2","없음","0"))
//        databaseReference.child("3").push().setValue(RankRecyclerItem("3","없음","0"))
//        databaseReference.child("4").push().setValue(RankRecyclerItem("4","없음","0"))
//        databaseReference.child("5").push().setValue(RankRecyclerItem("5","없음","0"))


        databaseReference.child("1").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                firstRankData = RankRecyclerItem(dataSnapshot.child("rank").value.toString(),dataSnapshot.child("name").value.toString(),dataSnapshot.child("score").value.toString())
                rankRecyclerItems.set(0, firstRankData)
                rankRecyclerAdapter.notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        databaseReference.child("2").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                secondRankData = RankRecyclerItem(dataSnapshot.child("rank").value.toString(),dataSnapshot.child("name").value.toString(),dataSnapshot.child("score").value.toString())
                rankRecyclerItems.set(1, secondRankData)
                rankRecyclerAdapter.notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        databaseReference.child("3").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                thirdRankData = RankRecyclerItem(dataSnapshot.child("rank").value.toString(),dataSnapshot.child("name").value.toString(),dataSnapshot.child("score").value.toString())
                rankRecyclerItems.set(2, thirdRankData)
                rankRecyclerAdapter.notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        databaseReference.child("4").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                fourthRankData = RankRecyclerItem(dataSnapshot.child("rank").value.toString(),dataSnapshot.child("name").value.toString(),dataSnapshot.child("score").value.toString())
                rankRecyclerItems.set(3, fourthRankData)
                rankRecyclerAdapter.notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        databaseReference.child("5").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                fifthRankData = RankRecyclerItem(dataSnapshot.child("rank").value.toString(),dataSnapshot.child("name").value.toString(),dataSnapshot.child("score").value.toString())
                rankRecyclerItems.set(4, fifthRankData)
                rankRecyclerAdapter.notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onCancelled(databaseError: DatabaseError) {}
        })




        btn_rank.setOnClickListener {
            if (score > firstRankData.score.toInt()) {
                fourDownFive()
                threeDownFour()
                twoDownThree()
                oneDownTwo()
                databaseReference.child("1").removeValue()
                databaseReference.child("1").push().setValue(RankRecyclerItem("1",edit_rank_name.text.toString(),score.toString()))
                Log.d("Debug","1번 들어옴")
            }

            if (score > secondRankData.score.toInt() && score <= firstRankData.score.toInt()) {
                fourDownFive()
                threeDownFour()
                twoDownThree()
                databaseReference.child("2").removeValue()
                databaseReference.child("2").push().setValue(RankRecyclerItem("2",edit_rank_name.text.toString(),score.toString()))
                Log.d("Deubg","2번 들어옴")
            }

            if (score > thirdRankData.score.toInt() && score <= secondRankData.score.toInt()) {
                fourDownFive()
                threeDownFour()
                databaseReference.child("3").removeValue()
                databaseReference.child("3").push().setValue(RankRecyclerItem("3",edit_rank_name.text.toString(),score.toString()))
            }

            if (score > fourthRankData.score.toInt() && score <= thirdRankData.score.toInt()) {
                fourDownFive()
                databaseReference.child("4").removeValue()
                databaseReference.child("4").push().setValue(RankRecyclerItem("4",edit_rank_name.text.toString(),score.toString()))
            }

            if (score > fifthRankData.score.toInt() && score <= fourthRankData.score.toInt()) {
                databaseReference.child("5").removeValue()
                databaseReference.child("5").push().setValue(RankRecyclerItem("5",edit_rank_name.text.toString(),score.toString()))
            }

        }
    }
    fun fourDownFive() {
        databaseReference.child("5").removeValue()
        fourthRankData.rank = "5"
        databaseReference.child("5").push().setValue(fourthRankData)
    }
    fun threeDownFour() {
        databaseReference.child("4").removeValue()
        thirdRankData.rank = "4"
        databaseReference.child("4").push().setValue(thirdRankData)
    }
    fun twoDownThree() {
        databaseReference.child("3").removeValue()
        secondRankData.rank = "3"
        databaseReference.child("3").push().setValue(secondRankData)
    }
    fun oneDownTwo() {
        databaseReference.child("2").removeValue()
        firstRankData.rank = "2"
        databaseReference.child("2").push().setValue(firstRankData)
    }
}
