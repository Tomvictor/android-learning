package com.technorip.kranioz.recyclerapi.Models

//http://kranioz.technorip.com/api/login/

class KraniozResponse(
    val err: ErrorResponse,
    val data: KraniozDataResponse
)

class KraniozDataResponse(
    val my_devices: List<DeviceDataResponse>,
    val authKey: String?,
    val userId: String?,
    val first_name: String,
    val last_name: String,
    val organization: String,
    val organization_website: String,
    val organization_mobileno: String,
    val organization_details: String,
    val profile_pic:String

)

class ErrorResponse(
    val message: String,
    val status: Int
)


class DeviceDataResponse(
    val title: String,
    val imei: String,
    val notes:String,
    val mode:String
)


//initial data response

class KraniozInitialResponse(
    val err: ErrorResponse,
    val data: KraniozDataResponse
)