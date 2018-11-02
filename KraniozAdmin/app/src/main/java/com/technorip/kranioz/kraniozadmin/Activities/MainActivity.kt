package com.technorip.kranioz.kraniozadmin.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.technorip.kranioz.kraniozadmin.R
import com.technorip.kranioz.kraniozadmin.Services.KraniozApiService
import com.technorip.kranioz.recyclerapi.Models.KraniozResponse
import com.technorip.kranioz.recyclerapi.Models.LoginRequest
import com.technorip.kranioz.recyclerapi.Models.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {


    private var edtUsername: EditText? = null
    private var edtPassword: EditText? = null
    private var txtBtnLogin: TextView? = null
    private var txtForgotPassword: TextView? = null
    private var tvMessage: TextView? = null

    private val KraniozLoginApiSer by lazy {
        KraniozApiService.create()
    }

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtUsername = findViewById(R.id.edt_username) as EditText
        edtPassword = findViewById(R.id.edt_password) as EditText
        txtBtnLogin = findViewById(R.id.txt_login_btn) as TextView
        txtForgotPassword = findViewById(R.id.tv_click_here) as TextView
        tvMessage = findViewById(R.id.tv_message) as TextView

        txtBtnLogin!!.setOnClickListener {attemptLogin() }


    }

    private fun attemptLogin() {
        val username = edtUsername!!.getText().toString().trim()
        val password = edtPassword!!.getText().toString().trim()

        val duration = Toast.LENGTH_SHORT

//        val toast = Toast.makeText(applicationContext, username +" "+ password, duration)
//        toast.show()

        //get the api response and populate the data
        var user = User()
        user.username = username
        user.passowrd = password
        var request = LoginRequest()
        request.user = user


        disposable = KraniozLoginApiSer.TryLogin("json", request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> loginResult(result) },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
            )
    }

    private fun loginResult(resData: KraniozResponse){


        var settings = getSharedPreferences("userDetails", Activity.MODE_PRIVATE)

        // Writing data to SharedPreferences
        val editor = settings.edit()
        editor.putString("authKey", resData.data.authKey)
        editor.apply()

        // Reading from SharedPreferences
        val value = settings.getString("authKey", "")

        val auth:String = resData.err.status.toString()

        if(auth=="1"){
//            open the discover page
            val discoverIntent = Intent(this, DiscoverActivity::class.java)
            ContextCompat.startActivity(this, discoverIntent, null)
            finish()
        }else{
//            show the error message
            Toast.makeText(applicationContext, resData.err.message.toString(), Toast.LENGTH_SHORT).show()

        }


    }


}
