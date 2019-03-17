package com.deghat.farhad.data.repository

import com.deghat.farhad.data.model.mapper.ManufacturersMapper
import com.deghat.farhad.data.remote.Remote
import com.deghat.farhad.domain.model.Manufacturers
import com.deghat.farhad.domain.repository.CarRepository
import io.reactivex.Observable

class CarRepositoryImpl: CarRepository {
    override fun getManufacturer(pageNumber: Int, pageSize: Int): Observable<Manufacturers> {
        return Remote().getManufacturer(pageNumber, pageSize).map { ManufacturersMapper().mapToDomain(it) }
    }
}