package edu.ndtho8205.colormyviews

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners() {
        val clickableViews = listOf<View>(
            tv_box_one,
            tv_box_two,
            tv_box_three,
            tv_box_four,
            tv_box_five,
            btn_red,
            btn_yellow,
            btn_green,
            layout
        )

        for (item in clickableViews) {
            item.setOnClickListener { makeColored(it) }
        }

    }

    private fun makeColored(view: View) {
        when (view.id) {
            R.id.tv_box_one -> view.setBackgroundColor(Color.DKGRAY)
            R.id.tv_box_two -> view.setBackgroundColor(Color.GRAY)
            R.id.tv_box_three -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.tv_box_four -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.tv_box_five -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.btn_red -> tv_box_three.setBackgroundResource(R.color.red)
            R.id.btn_yellow -> tv_box_four.setBackgroundResource(R.color.yellow)
            R.id.btn_green -> tv_box_five.setBackgroundResource(R.color.green)
            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}
