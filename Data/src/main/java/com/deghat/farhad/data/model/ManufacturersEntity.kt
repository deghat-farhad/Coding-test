package com.deghat.farhad.data.model

data class ManufacturersEntity(
        var page:Int,
        var pageSize: Int,
        var totalPageCount: Int,
        var wkda: Map<String, String> = mapOf()
)