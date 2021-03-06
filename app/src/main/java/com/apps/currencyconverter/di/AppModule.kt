package com.apps.currencyconverter.di

import com.apps.currencyconverter.data.CurrencyApi
import com.apps.currencyconverter.main.DefaultMainRepository
import com.apps.currencyconverter.main.MainRepository
import com.apps.currencyconverter.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


private const val BASE_URL = "http://api.exchangeratesapi.io"
@Module
@InstallIn(ApplicationComponentManager::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCurrencyApi():CurrencyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyApi::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(api : CurrencyApi):MainRepository = DefaultMainRepository(api)

    @Singleton
    @Provides
    fun provideDispatchers():DispatcherProvider = object : DispatcherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}