package com.deghat.farhad.data.remote

import com.deghat.farhad.data.model.CarTypes
import io.reactivex.Observable

const val BASE_URL = "***REMOVED***"
const val WA_KEY = "***REMOVED***"

class Remote {
    fun getCarTypes(pageNumber: Int, pageSize: Int): Observable<CarTypes>{
        return ServiceGenerator.carService.getCarTypes(pageNumber, pageSize, WA_KEY)
    }
}