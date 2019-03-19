package com.deghat.farhad.domain.repository

import com.deghat.farhad.domain.model.BuiltDates
import com.deghat.farhad.domain.model.MainTypes
import com.deghat.farhad.domain.model.Manufacturers
import io.reactivex.Observable

interface CarRepository {
    fun getManufacturer(pageNumber: Int, pageSize: Int): Observable<Manufacturers>
    fun getMainTypes(manufacturerId: String, pageNumber: Int, pageSize: Int): Observable<MainTypes>
    fun getBuiltDates(manufacturerId: String, mainType: String): Observable<BuiltDates>
}