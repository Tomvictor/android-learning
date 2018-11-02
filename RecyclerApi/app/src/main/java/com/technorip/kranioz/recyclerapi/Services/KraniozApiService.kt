package com.technorip.kranioz.recyclerapi.Services

import com.technorip.kranioz.recyclerapi.Models.KraniozResponse
import com.technorip.kranioz.recyclerapi.Models.LoginRequest
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface  KraniozLoginApiService {
    @POST("login/")
    fun TryLogin(@Query("format") format: String, @Body Payload: LoginRequest)
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


