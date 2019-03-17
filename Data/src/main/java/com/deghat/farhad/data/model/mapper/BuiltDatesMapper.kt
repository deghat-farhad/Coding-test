package com.deghat.farhad.data.model.mapper

import com.deghat.farhad.data.model.BuiltDatesEntity
import com.deghat.farhad.domain.model.BuiltDates

class BuiltDatesMapper {
    fun mapToDomain(builtDatesEntity: BuiltDatesEntity): BuiltDates{
        return BuiltDates(
                HashMap(builtDatesEntity.wkda)
        )
    }
}