

package com.example.android.materialme

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.*

/***
 * Main Activity for the Material Me app, a mock sports news application
 * with poor design choices.
 */
class MainActivity : AppCompatActivity() {

    // Member variables.
    private var mRecyclerView: RecyclerView? = null
    private var mSportsData: ArrayList<Sport>? = null
    private var mAdapter: SportsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView)

        // Set the Layout Manager.
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)

        // Initialize the ArrayList that will contain the data.
        mSportsData = ArrayList()

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = SportsAdapter(this, mSportsData)
        mRecyclerView!!.adapter = mAdapter

        // Get the data.
        initializeData()
    }

    /**
     * Initialize the sports data from resources.
     */
    private fun initializeData() {
        // Get the resources from the XML file.
        val sportsList = resources
                .getStringArray(R.array.sports_titles)
        val sportsInfo = resources
                .getStringArray(R.array.sports_info)

        // Clear the existing data (to avoid duplication).
        mSportsData!!.clear()

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for (i in sportsList.indices) {
            mSportsData!!.add(Sport(sportsList[i], sportsInfo[i]))
        }

        // Notify the adapter of the change.
        mAdapter!!.notifyDataSetChanged()
    }

}
