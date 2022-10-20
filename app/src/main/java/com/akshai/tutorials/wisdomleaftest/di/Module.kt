package com.akshai.tutorials.wisdomleaftest.di

import android.content.Context
import com.akshai.tutorials.wisdomleaftest.App
import com.akshai.tutorials.wisdomleaftest.network.ApiServices
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by ATM on 20/October/2022
 */

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun getGson(): Gson {
        return GsonBuilder().serializeNulls().setLenient().create()
    }


    @Provides
    @Singleton
    fun getInterceptor(): Interceptor {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }


    @Provides
    @Singleton
    fun getOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }


    @Provides
    @Singleton
    fun getRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("BASE_URL")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }


}