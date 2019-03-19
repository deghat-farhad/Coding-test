package com.deghat.farhad.domain.usecase

import com.deghat.farhad.domain.model.BuiltDates
import com.deghat.farhad.domain.repository.CarRepository
import com.deghat.farhad.domain.usecase.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler


class GetBuiltDates(
        private val carRepository: CarRepository,
        executorThread: Scheduler, uiThread: Scheduler)
    : UseCase<BuiltDates, GetBuiltDatesParams>(executorThread, uiThread) {
    override fun buildUseCaseObservable(params: GetBuiltDatesParams): Observable<BuiltDates> {
        return carRepository.getBuiltDates(params.manufacturerId, params.mainType)
    }
}

data class GetBuiltDatesParams(
        val manufacturerId: String,
        val mainType: String
)