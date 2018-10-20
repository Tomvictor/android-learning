package com.technorip.kranioz.kranioz

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {

    private val wikiApiServe by lazy {
        WikiApiService.create()
    }

    var disposable: Disposable? = null

    private val redditApiSer by lazy {
        RedditApiService.create()
    }

    var disposable2: Disposable? = null


    private fun redditSearch() {
        disposable2 = redditApiSer.getTop("", "10")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    var mydata: RedditChildrenResponse
                    var chdata: RedditNewsDataResponse
                    mydata = result.data.children[0]
//                    message.text = "${mydata.data.title}"
//                  implementing for loop
                    var totalString: String = ""
                    for (ivan in result.data.children){
                        totalString += ivan.data.title
                        totalString += "\r\n"
                        totalString += "\r\n"
                    }
                    message.text = totalString
                }

            )
    }

    private fun beginSearch(searchString: String) {
        disposable = wikiApiServe.hitCountCheck("query", "json", "search", searchString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> message.text = "${result.query.searchinfo.totalhits}" }
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

//                message.setText(R.string.title_home)
                beginSearch(searchString = "tom")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
//                message.setText(R.string.trips)
                redditSearch()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.locate)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
