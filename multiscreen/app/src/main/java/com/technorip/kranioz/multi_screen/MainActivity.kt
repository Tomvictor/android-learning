package com.technorip.kranioz.multi_screen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastMe(view: View){
    var mytost:Toast = Toast.makeText(this,"I am a Toast",Toast.LENGTH_LONG)
    mytost.show()
    }
}

