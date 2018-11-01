package com.technorip.kranioz.kraniozadmin.Activities


import android.app.Activity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.android.materialme.SportsAdapter
import com.technorip.kranioz.kraniozadmin.R
import com.technorip.kranioz.kraniozadmin.Services.KraniozLoginApiService
import com.technorip.kranioz.recyclerapi.Models.KraniozInitialResponse
import com.technorip.kranioz.recyclerapi.Models.Sport
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_discover.*
import java.util.*

class DiscoverActivity : AppCompatActivity() {

    companion object {
        const val ORGANIZATION = ""
        const val ORG_WEBSITE = ""
        const val ORG_PHONE = ""
        const val USER_NAME = ""
    }

    private var mRecyclerView: RecyclerView? = null
    private var mSportsData: ArrayList<Sport>? = null
    private var mAdapter: SportsAdapter? = null

    private val KraniozLoginApiSer by lazy {
        KraniozLoginApiService.create()
    }

    var disposable: Disposable? = null


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadHomeData()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView)

        // Set the Layout Manager.
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)

        // Initialize the ArrayList that will contain the data.
        mSportsData = ArrayList()

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = SportsAdapter(this, mSportsData!!)
        mRecyclerView!!.adapter = mAdapter

    }

    private fun loadHomeData(){
//        val name = intent.getIntExtra(USER_NAME,0)
//        val org = intent.getIntExtra(ORGANIZATION, 0)
//        val web = intent.getIntExtra(ORG_WEBSITE, 0)
//        val phone = intent.getIntExtra(ORG_PHONE, 0)
//        message.setText(phone)


        var settings = getSharedPreferences("userDetails", Activity.MODE_PRIVATE)
        val value = settings.getString("authKey", "")
        var jwtAuth:String = "JWT " + value

//        Toast.makeText(applicationContext,value, Toast.LENGTH_SHORT).show()

        disposable = KraniozLoginApiSer.InitialData("json",jwtAuth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> loadRecyclerView(result) },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
            )
    }

    private fun loadRecyclerView(response: KraniozInitialResponse){
        Toast.makeText(this, response.data.first_name, Toast.LENGTH_SHORT).show()
    }


}

