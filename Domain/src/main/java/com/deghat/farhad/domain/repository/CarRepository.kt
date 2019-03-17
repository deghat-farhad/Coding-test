package com.deghat.farhad.domain.repository

import com.deghat.farhad.domain.model.Manufacturers
import io.reactivex.Observable

interface CarRepository {
    fun getManufacturer(pageNumber: Int, pageSize: Int): Observable<Manufacturers>
}