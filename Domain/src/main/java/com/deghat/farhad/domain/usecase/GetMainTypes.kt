package com.deghat.farhad.domain.usecase

import com.deghat.farhad.domain.model.MainTypes
import com.deghat.farhad.domain.repository.CarRepository
import com.deghat.farhad.domain.usecase.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetMainTypes(
        private val carRepository: CarRepository,
        executorThread: Scheduler,
        uiThread: Scheduler)
    : UseCase<MainTypes, GetMainTypesParams>(executorThread, uiThread) {
    override fun buildUseCaseObservable(params: GetMainTypesParams): Observable<MainTypes> {
        return carRepository.getMainTypes(params.manufacturerId, params.pageNumber, params.pageSize)
    }
}

data class GetMainTypesParams(
        val manufacturerId: String,
        val pageNumber: Int = 0,
        val pageSize: Int = 0 //default value to get all items in a single page
)