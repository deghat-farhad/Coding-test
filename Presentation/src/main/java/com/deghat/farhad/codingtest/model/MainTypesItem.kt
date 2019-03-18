package com.deghat.farhad.codingtest.model

data class MainTypesItem(
        var page: Int,
        var pageSize: Int,
        var totalPageCount: Int,
        var wkda: ArrayList<MainType> = arrayListOf()
){
    data class MainType(
        val key: String,
        val value: String
    )
}