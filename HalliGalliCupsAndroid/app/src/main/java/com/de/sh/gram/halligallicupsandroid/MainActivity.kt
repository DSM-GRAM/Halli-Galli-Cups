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
import android.view.View.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img_main_red_cup.tag = "IMAGEVIEW_TAG"
        img_main_green_cup.tag="IMAGEVIEW_TAG"
        img_main_blue_cup.tag="IMAGEVIEW_TAG"
        img_main_black_cup.tag="IMAGEVIEW_TAG"

        img_main_red_cup.setOnTouchListener(TouchListener())
        img_main_green_cup.setOnTouchListener(TouchListener())
        img_main_blue_cup.setOnTouchListener(TouchListener())
        img_main_black_cup.setOnTouchListener(TouchListener())

        layout.setOnDragListener(DragListener())
        img_main_first_cup.setOnDragListener(DragListener())
        img_main_second_cup.setOnDragListener(DragListener())
        img_main_third_cup.setOnDragListener(DragListener())
        img_main_fourth_cup.setOnDragListener(DragListener())

    }

    class TouchListener : View.OnTouchListener {
        override fun onTouch(view: View, event: MotionEvent): Boolean {
            if (event.action == ACTION_DOWN) {
                val item = ClipData.Item(
                        view.tag as CharSequence)

                val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                val data = ClipData(view.tag.toString(),mimeTypes, item)
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

                    if(v.id == R.id.img_main_first_cup || v.id==R.id.img_main_second_cup || v.id==R.id.img_main_third_cup || v.id==R.id.img_main_fourth_cup) {
                        val view: View = event.localState as View
                        view.visibility = View.INVISIBLE
                        when (view.id) {
                            R.id.img_main_red_cup -> v.setBackgroundResource(R.drawable.red_cup)
                            R.id.img_main_green_cup -> v.setBackgroundResource(R.drawable.green_cup)
                            R.id.img_main_blue_cup -> v.setBackgroundResource(R.drawable.blue_cup)
                            R.id.img_main_black_cup -> v.setBackgroundResource(R.drawable.black_cup)
                        }
                    } else if(v.id == R.id.layout) {
                        val view: View = event.localState as View
                        view.visibility = VISIBLE
                    }
                }
            }
            return true
        }
    }
}
