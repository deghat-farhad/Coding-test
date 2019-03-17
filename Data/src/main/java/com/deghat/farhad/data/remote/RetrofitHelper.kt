package com.deghat.farhad.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import com.squareup.moshi.JsonAdapter



class RetrofitHelper {
    companion object {




        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        private val interceptor = Interceptor{ chain ->
            val request = chain.request().newBuilder()
                    .header("User-Agent", "CodingTest")
                    .build()
            chain.proceed(request)
        }

        val httpClient = OkHttpClient()
                .newBuilder()
                .addInterceptor(interceptor)
                .addInterceptor(getLogInterceptor())
                .build()


        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()!!

        private fun getLogInterceptor(): HttpLoggingInterceptor{
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return logInterceptor
        }
    }
}