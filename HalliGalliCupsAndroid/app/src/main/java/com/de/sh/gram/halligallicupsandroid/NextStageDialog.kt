package com.de.sh.gram.halligallicupsandroid

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.WindowManager
import kotlinx.android.synthetic.main.dialog_main_next_stage.*

class NextStageDialog(context: Context, var win: Boolean, var stage: Int, var score: Int,var cancelClickListener: View.OnClickListener, var nextStageClickListener: View.OnClickListener, var goRankClickListener: View.OnClickListener) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.8f
        window!!.attributes = lpWindow
        setContentView(R.layout.dialog_main_next_stage)

        if(win) {
            text_dialog_next_stage_win.visibility = VISIBLE
            text_dialog_next_stage_lose.visibility = INVISIBLE
        } else {
            text_dialog_next_stage_lose.visibility = VISIBLE
            text_dialog_next_stage_win.visibility = INVISIBLE
        }

        text_dialog_next_stage_stage.text = "STAGE : $stage"
        text_dialog_next_stage_score.text = "SCORE : $score"

        if(stage == 5) {
            btn_dialog_next_stage_next.text = "GO RANK"
            btn_dialog_next_stage_next.setOnClickListener(goRankClickListener)
        }

        if(cancelClickListener != null)
            btn_dialog_next_stage_cancel.setOnClickListener(cancelClickListener)
        if(nextStageClickListener != null)
            btn_dialog_next_stage_next.setOnClickListener(nextStageClickListener)
    }
}