package com.deghat.farhad.data.model.mapper

import com.deghat.farhad.data.model.ManufacturersEntity
import com.deghat.farhad.domain.model.Manufacturers

class ManufacturersMapper {
    companion object {
        fun mapToDomain(manufacturersEntity: ManufacturersEntity): Manufacturers{
            return Manufacturers(
                    manufacturersEntity.page,
                    manufacturersEntity.pageSize,
                    manufacturersEntity.totalPageCount,
                    HashMap(manufacturersEntity.wkda)
            )
        }
    }
}