package com.deghat.farhad.domain.usecase

import com.deghat.farhad.domain.model.Manufacturers
import com.deghat.farhad.domain.repository.CarRepository
import com.deghat.farhad.domain.usecase.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetManufacturers(
        private val carRepository: CarRepository,
        executorThread: Scheduler,
        uiThread: Scheduler)
    : UseCase<Manufacturers, GetManufacturerParams>(executorThread, uiThread) {

    override fun buildUseCaseObservable(params: GetManufacturerParams): Observable<Manufacturers> {
        return carRepository.getManufacturer(params.pageNumber, params.pageSize)
    }
}

data class GetManufacturerParams(
       val pageNumber: Int,
       val pageSize: Int
)