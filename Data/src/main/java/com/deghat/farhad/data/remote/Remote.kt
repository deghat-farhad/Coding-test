package com.deghat.farhad.data.remote

import com.deghat.farhad.data.model.BuiltDatesEntity
import com.deghat.farhad.data.model.ManufacturersEntity
import com.deghat.farhad.data.model.MainTypesEntity
import io.reactivex.Observable

const val BASE_URL = "***REMOVED***"
const val WA_KEY = "***REMOVED***"
//const val BASE_URL = "http://www.mocky.io/v2/"

class Remote(private val serviceGenerator: ServiceGenerator) {
    fun getManufacturer(pageNumber: Int, pageSize: Int): Observable<ManufacturersEntity>{
        return serviceGenerator.carService.getManufacturer(pageNumber, pageSize, WA_KEY)
    }

    fun getMainType(manufacturerId: String, pageNumber: Int, pageSize: Int): Observable<MainTypesEntity>{
        return serviceGenerator.carService
                .getMainTypes(manufacturerId, pageNumber, pageSize, WA_KEY)
    }

    fun getBuiltDates(manufacturerId: String, mainType: String): Observable<BuiltDatesEntity>{
        return serviceGenerator.carService.getBuiltDates(manufacturerId, mainType, WA_KEY)
    }
}