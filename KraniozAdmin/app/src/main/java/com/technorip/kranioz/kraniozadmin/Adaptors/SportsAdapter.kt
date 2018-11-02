

package com.example.android.materialme

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.technorip.kranioz.kraniozadmin.Activities.DetailActivity
import com.technorip.kranioz.kraniozadmin.R
import com.technorip.kranioz.recyclerapi.Models.Sport
import java.util.*




/***
 * The adapter class for the RecyclerView, contains the sports data.
 */
internal class SportsAdapter
/**
 * Constructor that passes in the sports data and the context.
 *
 * @param sportsData ArrayList containing the sports data.
 * @param context Context of the application.
 */
    (private val mContext: Context, // Member variables.
     private val mSportsData: ArrayList<Sport>) : RecyclerView.Adapter<SportsAdapter.ViewHolder>() {


    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added
     * after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): SportsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false))
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    override fun onBindViewHolder(holder: SportsAdapter.ViewHolder,
                                  position: Int) {
        // Get current sport.
        val currentSport = mSportsData[position]

        // Populate the textviews with data.
        holder.bindTo(currentSport)
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    override fun getItemCount(): Int {
        return mSportsData.size
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    internal inner class ViewHolder
    /**
     * Constructor for the ViewHolder, used in onCreateViewHolder().
     *
     * @param itemView The rootview of the list_item.xml layout file.
     */
        (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        // Member Variables for the TextViews
        private val mTitleText: TextView
        private val mInfoText: TextView
//        private val mSportsImage: ImageView

        init {
            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.subTitle)
            mInfoText = itemView.findViewById(R.id.newsTitle)
//            msubText = itemView.findViewById(R.id.newsTitle)
//            mSportsImage = itemView.findViewById(R.id.sportsImage)

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this)
        }

        fun bindTo(currentSport: Sport) {
            // Populate the textviews with data.
            mTitleText.text = currentSport.title
            mInfoText.text = currentSport.info

            // Load the images into the ImageView using the Glide library.
//            Glide.with(mContext).load(
//                currentSport.imageResource).into(mSportsImage)
        }

        /**
         * Handle click to show DetailActivity.
         *
         * @param view View that is clicked.
         */
        override fun onClick(view: View) {
            val currentitem = mSportsData[adapterPosition]
//            val detailIntent = Intent(mContext, DetailActivity::class.java)
//            detailIntent.putExtra("title", currentSport.title)
//            detailIntent.putExtra("image_resource",
//                currentSport.imageResource)
//            mContext.startActivity(detailIntent)

            val detailIntent = Intent(mContext, DetailActivity::class.java)
            detailIntent.putExtra("dev_id",currentitem.title)
            startActivity(mContext,detailIntent,null)

        }
    }
}