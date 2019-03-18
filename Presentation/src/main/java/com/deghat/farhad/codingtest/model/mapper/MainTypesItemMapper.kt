package com.deghat.farhad.codingtest.model.mapper

import com.deghat.farhad.codingtest.model.MainTypesItem
import com.deghat.farhad.domain.model.MainTypes

class MainTypesItemMapper {
    fun mapToPresentation(mainTypes: MainTypes): MainTypesItem {
        val mainTypesItem = MainTypesItem(
                mainTypes.page,
                mainTypes.pageSize,
                mainTypes.totalPageCount
        )

        for(mainType in mainTypes.wkda.toSortedMap()){
            mainTypesItem.wkda.add(
                    MainTypesItem.MainType(mainType.key, mainType.value))
        }


        return mainTypesItem
    }
}