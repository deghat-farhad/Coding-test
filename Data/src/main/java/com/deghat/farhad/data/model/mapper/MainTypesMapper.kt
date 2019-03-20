package com.deghat.farhad.data.model.mapper

import com.deghat.farhad.data.model.MainTypesEntity
import com.deghat.farhad.domain.model.MainTypes

class MainTypesMapper {
    companion object {
        fun mapToDomain(mainTypesEntity: MainTypesEntity): MainTypes {
            return MainTypes(
                    mainTypesEntity.page,
                    mainTypesEntity.pageSize,
                    mainTypesEntity.totalPageCount,
                    HashMap(mainTypesEntity.wkda)
            )
        }
    }
}