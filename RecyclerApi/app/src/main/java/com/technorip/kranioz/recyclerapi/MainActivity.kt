package com.example.android.materialme

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import com.technorip.kranioz.recyclerapi.RedditApiService
import com.technorip.kranioz.recyclerapi.RedditNewsResponse
import com.technorip.kranioz.recyclerapi.Sport
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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

    private val redditApiSer by lazy {
        RedditApiService.create()
    }

    var disposable: Disposable? = null


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
        mAdapter = SportsAdapter(this, mSportsData!!)
        mRecyclerView!!.adapter = mAdapter

        // Get the data.
//        initializeData()
        initialApiData()

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

    private fun initialApiData() {
//        get the api response and populate the data
        disposable = redditApiSer.getTop("", "25")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> loadData(result) },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
            )
    }

    private fun loadData(apiRes: RedditNewsResponse) {
        // Clear the existing data (to avoid duplication).
        mSportsData!!.clear()

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.

        for (i in apiRes.data.children){
            mSportsData!!.add(
                Sport(i.data.author, i.data.title,i.data.thumbnail
                )
            )
        }


        // Notify the adapter of the change.
        mAdapter!!.notifyDataSetChanged()
    }


}
