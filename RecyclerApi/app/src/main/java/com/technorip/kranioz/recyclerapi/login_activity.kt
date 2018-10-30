package com.technorip.kranioz.recyclerapi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.android.materialme.R

class login_activity : AppCompatActivity() {

    private var edtUsername: EditText? = null
    private var edtPassword: EditText? = null
    private var txtBtnLogin: TextView? = null
    private var txtForgotPassword: TextView? = null
    private var tvMessage: TextView? = null

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

        val toast = Toast.makeText(applicationContext, username +" "+ password, duration)
        toast.show()

    }
}
