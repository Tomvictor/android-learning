package com.technorip.kranioz.kraniozadmin.Activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.technorip.kranioz.kraniozadmin.R
import com.technorip.kranioz.kraniozadmin.Services.KraniozApiService
import com.technorip.kranioz.recyclerapi.Models.KraniozDeviceDetailResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailActivity : AppCompatActivity() {



    private val KraniozApiSer by lazy {
        KraniozApiService.create()
    }

    var disposable: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title:String = intent.getStringExtra("title")
        val info:String = intent.getStringExtra("info")
        val id:String = intent.getStringExtra("id")

        loadDeviceData(id)

    }

    private fun loadDeviceData(devid:String){

        var settings = getSharedPreferences("userDetails", Activity.MODE_PRIVATE)
        val value = settings.getString("authKey", "")
        var jwtAuth:String = "JWT " + value

        disposable = KraniozApiSer.DeviceDetail("json",devid,authKey = jwtAuth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> displayData(result) },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
            )
    }

    private fun displayData(apiRes: KraniozDeviceDetailResponse){
//        Toast.makeText(applicationContext,apiRes.data.tracking_mode_title, Toast.LENGTH_SHORT).show()

        val mImei: TextView = findViewById(R.id.imei)
        val mDevId: TextView = findViewById(R.id.devid)
        val mTitle: TextView = findViewById(R.id.title)
        val mNotes: TextView = findViewById(R.id.notes)
        val mDeviceStatus: TextView = findViewById(R.id.deviceStatus)
        val mTrackingMode: TextView = findViewById(R.id.trackingmode)

        mImei.text = apiRes.data.imei
        mDevId.text = apiRes.data.id.toString()
        mTitle.text = apiRes.data.title
        mNotes.text = apiRes.data.notes
        mDeviceStatus.text = apiRes.data.device_status_title
        mTrackingMode.text = apiRes.data.tracking_mode_title




    }
}
