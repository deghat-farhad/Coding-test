package com.deghat.farhad.data.remote

import com.deghat.farhad.data.model.BuiltDatesEntity
import com.deghat.farhad.data.model.ManufacturersEntity
import com.deghat.farhad.data.model.MainTypesEntity
import io.reactivex.Observable

const val BASE_URL = "***REMOVED***"
const val WA_KEY = "***REMOVED***"

class Remote {
    fun getManufacturer(pageNumber: Int, pageSize: Int): Observable<ManufacturersEntity>{
        return ServiceGenerator.carService.getManufacturer(pageNumber, pageSize, WA_KEY)
    }

    fun getMainType(manufacturerId: Int, pageNumber: Int, pageSize: Int): Observable<MainTypesEntity>{
        return ServiceGenerator.carService
                .getMainTypes(manufacturerId, pageNumber, pageSize, WA_KEY)
    }

    fun getBuiltDates(manufacturerId: Int, mainType: String): Observable<BuiltDatesEntity>{
        return ServiceGenerator.carService.getBuiltDates(manufacturerId, mainType, WA_KEY)
    }
}