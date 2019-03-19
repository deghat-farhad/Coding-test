package com.deghat.farhad.codingtest.model.mapper

import com.deghat.farhad.codingtest.model.BuiltDatesItem
import com.deghat.farhad.domain.model.BuiltDates

class BuiltDatesItemMapper {
    fun mapToPresentation(builtDates: BuiltDates): BuiltDatesItem{

        val builtDatesList = arrayListOf<BuiltDatesItem.BuiltDate>()
        for (builtDate in builtDates.wkda){
            builtDatesList.add(BuiltDatesItem.BuiltDate(builtDate.key, builtDate.value))
        }

        return BuiltDatesItem(
                builtDatesList
        )
    }
}