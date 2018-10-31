package com.technorip.kranioz.recyclerapi.Services

import com.technorip.kranioz.recyclerapi.Models.KraniozResponse
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface  KraniozLoginApiService {
    @Headers("Content-Type: application/json")
    @POST("login/")
    fun TryLogin(@Body Payload: JSONObject)
            : Observable<KraniozResponse>

    companion object {
        fun create(): KraniozLoginApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://kranioz.technorip.com/api/")
                .build()
            return retrofit.create(KraniozLoginApiService::class.java)
        }
    }
}


