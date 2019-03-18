package com.deghat.farhad.codingtest.model.mapper

import com.deghat.farhad.codingtest.model.ManufacturersItem
import com.deghat.farhad.domain.model.Manufacturers

class ManufacturersItemMapper {
    fun mapToPresentation(manufacturers: Manufacturers): ManufacturersItem{
        val manufacturersItem = ManufacturersItem(
                manufacturers.page,
                manufacturers.pageSize,
                manufacturers.totalPageCount
        )

        for(manufacturer in manufacturers.wkda.toSortedMap()){
            manufacturersItem.wkda.add(
                    ManufacturersItem.Manufacturer(manufacturer.key, manufacturer.value))
        }


        return manufacturersItem
    }
}