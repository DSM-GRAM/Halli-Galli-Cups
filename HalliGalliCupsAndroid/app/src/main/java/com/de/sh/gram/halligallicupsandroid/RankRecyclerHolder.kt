package com.de.sh.gram.halligallicupsandroid

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class RankRecyclerHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var rankText: TextView
    var nameText: TextView
    var scoreText: TextView

    init {
        rankText = itemView.findViewById(R.id.text_rank_rank)
        nameText = itemView.findViewById(R.id.text_rank_name)
        scoreText = itemView.findViewById(R.id.text_rank_score)
    }
}