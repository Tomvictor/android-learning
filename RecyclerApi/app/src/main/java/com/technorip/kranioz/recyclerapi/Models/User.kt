package com.technorip.kranioz.recyclerapi.Models

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("email")
    var username: String? = null

    @SerializedName("password")
    var passowrd: String? = null
}