package com.technorip.kranioz.multi_screen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastMe(view: View){
    var mytost:Toast = Toast.makeText(this,"Toast me?",Toast.LENGTH_LONG)
    mytost.show()
    }

    fun countMe(view: View){
        var showCountTextView = findViewById(R.id.counter) as TextView
        val countString = showCountTextView.text.toString()

        var count: Int = Integer.parseInt(countString)
        count++
        showCountTextView.text = count.toString()
    }
}

