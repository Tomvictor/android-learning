package com.technorip.kranioz.multi_screen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*


class randon_activity : AppCompatActivity() {

    companion object {
        const val TOTAL_COUNT = "total_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_randon_activity)
        showRandomNumber()
    }

    fun showRandomNumber() {
        // Get the count from the intent extras
        val count = intent.getIntExtra(TOTAL_COUNT, 0)

        // Generate the random number
        val random = Random()
        var randomInt = 0
        // Add one because the bound is exclusive
        if (count > 0) {
            // Add one because the bound is exclusive
            randomInt = random.nextInt(count + 1)
        }

        // Display the random number.
        val textview_random = findViewById(R.id.textview_random) as TextView
        textview_random.text = Integer.toString(randomInt)

//        // Substitute the max value into the string resource
//        // for the heading, and update the heading
//        textview_random.text = getString(R.string.random_heading, count)
    }
}
