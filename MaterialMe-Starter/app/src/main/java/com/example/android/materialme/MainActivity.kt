

package com.example.android.materialme

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
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

        // Helper class for creating swipe to dismiss and drag and drop
        // functionality.
        val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or
                        ItemTouchHelper.DOWN or ItemTouchHelper.UP,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            /**
             * Defines the drag and drop functionality.
             *
             * @param recyclerView The RecyclerView that contains the list items
             * @param viewHolder The SportsViewHolder that is being moved
             * @param target The SportsViewHolder that you are switching the
             * original one with.
             * @return true if the item was moved, false otherwise
             */
            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                // Get the from and to positions.
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition

                // Swap the items and notify the adapter.
                Collections.swap(mSportsData!!, from, to)
                mAdapter!!.notifyItemMoved(from, to)
                return true
            }

            /**
             * Defines the swipe to dismiss functionality.
             *
             * @param viewHolder The viewholder being swiped.
             * @param direction The direction it is swiped in.
             */
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder,
                                  direction: Int) {
                // Remove the item from the dataset.
                mSportsData!!.removeAt(viewHolder.adapterPosition)
                // Notify the adapter.
                mAdapter!!.notifyItemRemoved(viewHolder.adapterPosition)
            }
        })

        // Attach the helper to the RecyclerView.
        helper.attachToRecyclerView(mRecyclerView)

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
        val sportsImageResources = resources
                .obtainTypedArray(R.array.sports_images)

        // Clear the existing data (to avoid duplication).
        mSportsData!!.clear()

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for (i in sportsList.indices) {
            mSportsData!!.add(Sport(sportsList[i], sportsInfo[i],
                    sportsImageResources.getResourceId(i,0)))
        }

        // Recycle the typed array.
        sportsImageResources.recycle()

        // Notify the adapter of the change.
        mAdapter!!.notifyDataSetChanged()
    }



}
