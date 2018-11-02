package com.technorip.kranioz.recyclerapi.Activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.android.materialme.R
import com.technorip.kranioz.recyclerapi.Models.KraniozResponse
import com.technorip.kranioz.recyclerapi.Models.LoginRequest
import com.technorip.kranioz.recyclerapi.Models.User
import com.technorip.kranioz.recyclerapi.Services.KraniozLoginApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class login_activity : AppCompatActivity() {

    private var edtUsername: EditText? = null
    private var edtPassword: EditText? = null
    private var txtBtnLogin: TextView? = null
    private var txtForgotPassword: TextView? = null
    private var tvMessage: TextView? = null

    private val KraniozLoginApiSer by lazy {
        KraniozLoginApiService.create()
    }

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activity)

        edtUsername = findViewById(R.id.edt_username) as EditText
        edtPassword = findViewById(R.id.edt_password) as EditText
        txtBtnLogin = findViewById(R.id.txt_login_btn) as TextView
        txtForgotPassword = findViewById(R.id.tv_click_here) as TextView
        tvMessage = findViewById(R.id.tv_message) as TextView

        txtBtnLogin!!.setOnClickListener {attemptLogin() }
//        txtForgotPassword!!.setOnClickListener {presenter!!.forgotPassword() }

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

        Toast.makeText(applicationContext, resData.data.organization, Toast.LENGTH_SHORT).show()

        var settings = getSharedPreferences("userDetails", Activity.MODE_PRIVATE)

        // Writing data to SharedPreferences
        val editor = settings.edit()
        editor.putString("authKey", resData.data.authKey)
        editor.apply()

        // Reading from SharedPreferences
//        val value = settings.getString("authKey", "")

//        Toast.makeText(applicationContext, value, Toast.LENGTH_SHORT).show()






//        persistenceServices.saveString(GlobalConstants.USER_AUTH_KEY, authKey)
    }


}
