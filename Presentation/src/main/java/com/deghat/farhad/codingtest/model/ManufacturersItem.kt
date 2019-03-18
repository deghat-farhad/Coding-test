package com.deghat.farhad.codingtest.model

data class ManufacturersItem(
        var page:Int,
        var pageSize: Int,
        var totalPageCount: Int,
        var wkda: ArrayList<Manufacturer> = arrayListOf()
){
    data class Manufacturer(
            val key: String,
            val value: String
    )
}