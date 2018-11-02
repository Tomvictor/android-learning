package com.technorip.kranioz.kraniozadmin.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.technorip.kranioz.kraniozadmin.R

class DetailActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val devId:String = intent.getStringExtra("dev_id")
        Toast.makeText(applicationContext,devId, Toast.LENGTH_SHORT).show()

    }
}
