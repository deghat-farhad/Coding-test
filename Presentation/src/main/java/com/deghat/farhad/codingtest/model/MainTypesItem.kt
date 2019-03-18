package com.deghat.farhad.codingtest.model

data class MainTypesItem(
        var page: Int,
        var pageSize: Int,
        var totalPageCount: Int,
        var wkda: Map<String, String> = mapOf()
)