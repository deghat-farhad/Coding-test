package com.deghat.farhad.codingtest.model.mapper

import com.deghat.farhad.codingtest.model.BuiltDatesItem
import com.deghat.farhad.domain.model.BuiltDates

class BuiltDatesItemMapper {
    fun mapToPresentation(builtDates: BuiltDates): BuiltDatesItem{
        return BuiltDatesItem(
                HashMap(builtDates.wkda)
        )
    }
}