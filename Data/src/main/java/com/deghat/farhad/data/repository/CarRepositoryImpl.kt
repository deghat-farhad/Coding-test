package com.deghat.farhad.data.repository

import com.deghat.farhad.data.model.mapper.BuiltDatesMapper
import com.deghat.farhad.data.model.mapper.MainTypesMapper
import com.deghat.farhad.data.model.mapper.ManufacturersMapper
import com.deghat.farhad.data.remote.Remote
import com.deghat.farhad.domain.model.BuiltDates
import com.deghat.farhad.domain.model.MainTypes
import com.deghat.farhad.domain.model.Manufacturers
import com.deghat.farhad.domain.repository.CarRepository
import io.reactivex.Observable

class CarRepositoryImpl: CarRepository {
    override fun getManufacturer(pageNumber: Int, pageSize: Int): Observable<Manufacturers> {
        return Remote().getManufacturer(pageNumber, pageSize).map { ManufacturersMapper().mapToDomain(it) }
    }

    override fun getMainTypes(manufacturerId: String, pageNumber: Int, pageSize: Int): Observable<MainTypes> {
        return Remote().getMainType(manufacturerId, pageNumber, pageSize).map { MainTypesMapper().mapToDomain(it) }
    }

    override fun getBuiltDates(manufacturerId: Int, mainType: String): Observable<BuiltDates> {
        return Remote().getBuiltDates(manufacturerId, mainType).map { BuiltDatesMapper().mapToDomain(it) }
    }
}