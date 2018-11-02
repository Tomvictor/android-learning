//package com.technorip.kranioz.recyclerapi.Services
//
//import android.R.id.edit
//import android.content.Context.MODE_PRIVATE
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.app.Activity
//import android.util.Log
//import com.example.android.materialme.R
//
//
//class SharedPrefsActivity : Activity() {
//
//    public override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.abc_expanded_menu_layout)
//
//        val settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
//
//        // Writing data to SharedPreferences
//        val editor = settings.edit()
//        editor.putString("key", "some value")
//        editor.commit()
//
//        // Reading from SharedPreferences
//        val value = settings.getString("key", "")
//        Log.d(TAG, value)
//    }
//
//    companion object {
//
//        val PREFS_NAME = "MyApp_Settings"
//    }
//}


