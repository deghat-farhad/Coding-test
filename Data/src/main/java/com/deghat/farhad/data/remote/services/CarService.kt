package com.deghat.farhad.data.remote.services

import com.deghat.farhad.data.model.BuiltDatesEntity
import com.deghat.farhad.data.model.ManufacturersEntity
import com.deghat.farhad.data.model.MainTypesEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CarService {

    //@GET("v1/car-types/manufacturer")
    @GET("5c8f2b0c300000532c1b10f5")
    fun getManufacturer(@Query("page") pageNumber: Int,
                        @Query("pageSize") pageSize: Int,
                        @Query("wa_key") waKey: String): Observable<ManufacturersEntity>

    //@GET("v1/car-types/main-types")
    @GET("5c8f2df9300000962b1b1100")
    fun getMainTypes(@Query("manufacturer") manufacturerId: Int,
                     @Query("page") pageNumber: Int,
                     @Query("pageSize") pageSize: Int,
                     @Query("wa_key") waKey: String): Observable<MainTypesEntity>

    //@GET("v1/car-types/built-dates")
    @GET("5c8f2ec4300000152a1b1104")
    fun getBuiltDates(@Query("manufacturer") manufacturerId: Int,
                      @Query("main-type") mainType: String,
                      @Query("wa_key") waKey: String): Observable<BuiltDatesEntity>
}