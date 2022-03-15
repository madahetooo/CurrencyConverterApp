package com.apps.currencyconverter.data

import com.apps.currencyconverter.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("/latest")
    suspend fun getRates(
        @Query("base") base:String
    ):Response<CurrencyResponse>
}