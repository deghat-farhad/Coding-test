package com.deghat.farhad.data.remote

import com.deghat.farhad.data.remote.services.CarService

class ServiceGenerator {
    companion object {
        val carService = RetrofitHelper.retrofit.create(CarService::class.java)
    }
}