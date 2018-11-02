package com.technorip.kranioz.recyclerapi.Models

import com.google.gson.annotations.SerializedName

class LoginRequest {

    @SerializedName("user")
    var user: User? = null
}
