package com.deghat.farhad.codingtest.model.mapper

import com.deghat.farhad.codingtest.model.MainTypesItem
import com.deghat.farhad.domain.model.MainTypes

class MainTypesItemMapper {
    fun mapToPresentation(mainTypes: MainTypes): MainTypesItem {
        return MainTypesItem(
                mainTypes.page,
                mainTypes.pageSize,
                mainTypes.totalPageCount,
                HashMap(mainTypes.wkda)
        )
    }
}