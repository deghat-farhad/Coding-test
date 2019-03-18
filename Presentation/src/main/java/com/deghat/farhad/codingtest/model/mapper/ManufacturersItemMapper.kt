package com.deghat.farhad.codingtest.model.mapper

import com.deghat.farhad.codingtest.model.ManufacturersItem
import com.deghat.farhad.domain.model.Manufacturers

class ManufacturersItemMapper {
    fun mapToPresentation(manufacturers: Manufacturers): ManufacturersItem{
        return ManufacturersItem(
                manufacturers.page,
                manufacturers.pageSize,
                manufacturers.totalPageCount,
                HashMap(manufacturers.wkda)
        )
    }
}