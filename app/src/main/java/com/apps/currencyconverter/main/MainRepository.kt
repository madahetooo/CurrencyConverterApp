package com.apps.currencyconverter.main

import com.apps.currencyconverter.data.models.CurrencyResponse
import com.apps.currencyconverter.util.Resource

interface MainRepository {
    suspend fun getRates(base:String) : Resource<CurrencyResponse>
}