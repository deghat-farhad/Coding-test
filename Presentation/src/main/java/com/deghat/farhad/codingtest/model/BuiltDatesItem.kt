package com.deghat.farhad.codingtest.model

data class BuiltDatesItem(
        var wkda: ArrayList<BuiltDate> = arrayListOf()
){
    data class BuiltDate(
            val key: String,
            val value: String
    )
}