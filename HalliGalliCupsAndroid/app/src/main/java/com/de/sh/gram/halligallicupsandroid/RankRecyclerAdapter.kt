package com.de.sh.gram.halligallicupsandroid

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.ArrayList

class RankRecyclerAdapter(itemList: ArrayList<RankRecyclerItem>) : RecyclerView.Adapter<RankRecyclerHolder>() {
    private val mainRecyclerConditionItems: ArrayList<RankRecyclerItem>

    init {
        mainRecyclerConditionItems = itemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankRecyclerHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rank, parent, false)
        return RankRecyclerHolder(v)
    }

    override fun onBindViewHolder(holder: RankRecyclerHolder, position: Int) {
        holder.rankText.text = mainRecyclerConditionItems[position].rank
        holder.nameText.text = mainRecyclerConditionItems[position].name
        holder.scoreText.text = mainRecyclerConditionItems[position].score
    }

    override fun getItemCount(): Int {
        return mainRecyclerConditionItems.size
    }
}