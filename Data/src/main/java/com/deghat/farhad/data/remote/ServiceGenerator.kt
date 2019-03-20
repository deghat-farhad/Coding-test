package com.deghat.farhad.data.remote

import com.deghat.farhad.data.remote.services.CarService
import retrofit2.Retrofit

class ServiceGenerator(retrofit: Retrofit) {
    val carService = retrofit.create(CarService::class.java)
}