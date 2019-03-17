package com.deghat.farhad.domain.model

data class Manufacturers(
        var page:Int,
        var pageSize: Int,
        var totalPageCount: Int,
        var wkda: Map<String, String> = mapOf()
)