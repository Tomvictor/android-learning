package com.technorip.kranioz.kraniozadmin.Services

import com.technorip.kranioz.recyclerapi.Models.KraniozDeviceDetailResponse
import com.technorip.kranioz.recyclerapi.Models.KraniozInitialResponse
import com.technorip.kranioz.recyclerapi.Models.KraniozResponse
import com.technorip.kranioz.recyclerapi.Models.LoginRequest
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface  KraniozApiService {
    @POST("login/")
    fun TryLogin(@Query("format") format: String, @Body Payload: LoginRequest)
            : Observable<KraniozResponse>


    @GET("initial/data/")
    fun InitialData(@Query("format") format: String,@Header("Authorization") authKey:String)
            : Observable<KraniozInitialResponse>

    @GET("detail/")
    fun DeviceDetail(@Query("format") format: String,@Query("q") q: String,@Header("Authorization") authKey:String)
            : Observable<KraniozDeviceDetailResponse>

    companion object {
        fun create(): KraniozApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://kranioz.technorip.com/api/")
                .build()
            return retrofit.create(KraniozApiService::class.java)
        }
    }
}


