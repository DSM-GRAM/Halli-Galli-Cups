package com.de.sh.gram.halligallicupsandroid

import android.content.ClipData
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.content.ClipDescription
import android.util.Log
import android.view.DragEvent
import android.view.Gravity
import android.view.View.*
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import java.util.*


class MainActivity : AppCompatActivity() {
    var score: Int = 0
    var stage: Int = 0
    lateinit var nextStageDialog: NextStageDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var randomNum = Random().nextInt(17)
        nextStageDialog = NextStageDialog(this, nextStageCancelClickListener, nextStageClickListener)

        var cards: ArrayList<Card> = ArrayList<Card>()
        val card1: Card = Card(arrayOf("red", "blue", "green", "black"))
        val card2: Card = Card(arrayOf("red", "green", "black", "blue"))
        val card3: Card = Card(arrayOf("red", "black", "blue", "green"))
        val card4: Card = Card(arrayOf("red", "blue", "black", "green"))
        val card5: Card = Card(arrayOf("blue", "black", "green", "red"))
        val card6: Card = Card(arrayOf("blue", "green", "red", "black"))
        val card7: Card = Card(arrayOf("blue", "red", "black", "green"))
        val card8: Card = Card(arrayOf("blue", "black", "red", "green"))
        val card9: Card = Card(arrayOf("black", "red", "blue", "green"))
        val card10: Card = Card(arrayOf("black", "green", "red", "blue"))
        val card11: Card = Card(arrayOf("black", "blue", "red", "green"))
        val card12: Card = Card(arrayOf("black", "red", "green", "blue"))
        val card13: Card = Card(arrayOf("black", "blue", "green", "red"))
        val card14: Card = Card(arrayOf("green", "blue", "black", "red"))
        val card15: Card = Card(arrayOf("green", "black", "blue", "red"))
        val card16: Card = Card(arrayOf("green", "red", "blue", "black"))
        val card17: Card = Card(arrayOf("green", "red", "black", "blue"))

        cards.add(card1)
        cards.add(card2)
        cards.add(card3)
        cards.add(card4)
        cards.add(card5)
        cards.add(card6)
        cards.add(card7)
        cards.add(card8)
        cards.add(card9)
        cards.add(card10)
        cards.add(card11)
        cards.add(card12)
        cards.add(card13)
        cards.add(card14)
        cards.add(card15)
        cards.add(card16)
        cards.add(card17)

        cards.add(Card(arrayOf("")))


        img_main_red_cup.tag = "IMAGEVIEW_TAG"
        img_main_green_cup.tag = "IMAGEVIEW_TAG"
        img_main_blue_cup.tag = "IMAGEVIEW_TAG"
        img_main_black_cup.tag = "IMAGEVIEW_TAG"

        img_main_red_cup.setOnTouchListener(TouchListener())
        img_main_green_cup.setOnTouchListener(TouchListener())
        img_main_blue_cup.setOnTouchListener(TouchListener())
        img_main_black_cup.setOnTouchListener(TouchListener())

        layout.setOnDragListener(DragListener())
        img_main_first_cup.setOnDragListener(DragListener())
        img_main_first_cup.setOnClickListener { imageComebackCheck(img_main_first_cup) }
        img_main_second_cup.setOnDragListener(DragListener())
        img_main_second_cup.setOnClickListener { imageComebackCheck(img_main_second_cup) }
        img_main_third_cup.setOnDragListener(DragListener())
        img_main_third_cup.setOnClickListener { imageComebackCheck(img_main_third_cup) }
        img_main_fourth_cup.setOnDragListener(DragListener())
        img_main_fourth_cup.setOnClickListener { imageComebackCheck(img_main_fourth_cup) }

        text_main_score.text = "SCORE : " + score.toString()
        text_main_stage.text = "STAGE : " + stage.toString()

        btn_main_bell.setOnClickListener {
            var yourArray: Array<String> = arrayOf("", "", "", "")
            when (img_main_first_cup.tag) {
                R.drawable.red_cup -> yourArray[0] = "red"
                R.drawable.green_cup -> yourArray[0] = "green"
                R.drawable.blue_cup -> yourArray[0] = "blue"
                R.drawable.black_cup -> yourArray[0] = "black"
            }
            when (img_main_second_cup.tag) {
                R.drawable.red_cup -> yourArray[1] = "red"
                R.drawable.green_cup -> yourArray[1] = "green"
                R.drawable.blue_cup -> yourArray[1] = "blue"
                R.drawable.black_cup -> yourArray[1] = "black"
            }
            when (img_main_third_cup.tag) {
                R.drawable.red_cup -> yourArray[2] = "red"
                R.drawable.green_cup -> yourArray[2] = "green"
                R.drawable.blue_cup -> yourArray[2] = "blue"
                R.drawable.black_cup -> yourArray[2] = "black"
            }
            when (img_main_fourth_cup.tag) {
                R.drawable.red_cup -> yourArray[3] = "red"
                R.drawable.green_cup -> yourArray[3] = "green"
                R.drawable.blue_cup -> yourArray[3] = "blue"
                R.drawable.black_cup -> yourArray[3] = "black"
            }

            Log.d("Debug", cards[randomNum].cardArray[0] + cards[randomNum].cardArray[1] + cards[randomNum].cardArray[2] + cards[randomNum].cardArray[3])
            if (cards[randomNum].match(yourArray)) {
                Toast.makeText(this, "성공", LENGTH_SHORT).show()
                nextStageDialog.setCancelable(true)
                nextStageDialog.window.setGravity(Gravity.CENTER)
                nextStageDialog.show()
            } else {
                Toast.makeText(this, "실패", LENGTH_SHORT).show()
            }
        }


    }

    class TouchListener : View.OnTouchListener {
        override fun onTouch(view: View, event: MotionEvent): Boolean {
            if (event.action == ACTION_DOWN) {
                val item = ClipData.Item(
                        view.tag as CharSequence)

                val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                val data = ClipData(view.tag.toString(), mimeTypes, item)
                val shadowBuilder = View.DragShadowBuilder(view)

                view.startDrag(data, shadowBuilder, view, 0)

                view.visibility = View.INVISIBLE

                return true
            }
            return false
        }

    }

    class DragListener : View.OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            when (event.action) {

                DragEvent.ACTION_DROP -> {
                    Log.d("DragClickListener", "ACTION_DROP")

                    if (v.id == R.id.img_main_first_cup || v.id == R.id.img_main_second_cup || v.id == R.id.img_main_third_cup || v.id == R.id.img_main_fourth_cup) {
                        val view: View = event.localState as View
                        view.visibility = View.INVISIBLE
                        when (view.id) {
                            R.id.img_main_red_cup -> {
                                v.setBackgroundResource(R.drawable.red_cup)
                                v.tag = R.drawable.red_cup
                            }
                            R.id.img_main_green_cup -> {
                                v.setBackgroundResource(R.drawable.green_cup)
                                v.tag = R.drawable.green_cup
                            }
                            R.id.img_main_blue_cup -> {
                                v.setBackgroundResource(R.drawable.blue_cup)
                                v.tag = R.drawable.blue_cup
                            }
                            R.id.img_main_black_cup -> {
                                v.setBackgroundResource(R.drawable.black_cup)
                                v.tag = R.drawable.black_cup
                            }
                        }
                    } else if (v.id == R.id.layout) {
                        val view: View = event.localState as View
                        view.visibility = VISIBLE
                    }
                }
            }
            return true
        }
    }

    fun imageComebackCheck(v: View) {
        when (v.tag) {
            R.drawable.red_cup -> {
                img_main_red_cup.visibility = VISIBLE
                v.setBackgroundResource(R.drawable.empty_image_background)
            }
            R.drawable.green_cup -> {
                img_main_green_cup.visibility = VISIBLE
                v.setBackgroundResource(R.drawable.empty_image_background)
            }
            R.drawable.blue_cup -> {
                img_main_blue_cup.visibility = VISIBLE
                v.setBackgroundResource(R.drawable.empty_image_background)
            }
            R.drawable.black_cup -> {
                img_main_black_cup.visibility = VISIBLE
                v.setBackgroundResource(R.drawable.empty_image_background)
            }
        }
    }

    class Card(var cardArray: Array<String>) {

        fun match(yourArray: Array<String>): Boolean {
            Log.d("Debug", "cardArray : " + cardArray[0] + cardArray[1] + cardArray[2] + cardArray[3])
            Log.d("Debug", "yourArray : " + yourArray[0] + yourArray[1] + yourArray[2] + yourArray[3])
            for (i in 0..3) {
                if (!(cardArray[i].equals(yourArray[i]))) {
                    return false
                }
            }
            return true
        }
    }

    val nextStageCancelClickListener: OnClickListener = OnClickListener { nextStageDialog.dismiss() }

    val nextStageClickListener = OnClickListener {
        nextStageDialog.dismiss()
        stage++
        text_main_stage.text = "STAGE : " + stage.toString()
    }
}
