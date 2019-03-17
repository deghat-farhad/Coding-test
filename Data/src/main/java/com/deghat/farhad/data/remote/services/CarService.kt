package com.deghat.farhad.data.remote.services

import com.deghat.farhad.data.model.CarTypes
import com.deghat.farhad.data.model.MainTypes
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CarService {

    @GET("v1/car-types/manufacturer")
    fun getCarTypes(@Query("page") pageNumber: Int,
                    @Query("pageSize") pageSize: Int,
                    @Query("wa_key") waKey: String): Observable<CarTypes>

    @GET("v1/car-types/main-types")
    fun getMainTypes(@Query("manufacturer") manufacturerId: Int,
                     @Query("page") pageNumber: Int,
                     @Query("pageSize") pageSize: Int,
                     @Query("wa_key") waKey: String): Observable<MainTypes>
}