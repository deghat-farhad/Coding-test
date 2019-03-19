package com.deghat.farhad.codingtest.builtDatesList

import com.deghat.farhad.codingtest.CommonView

interface BuiltDatesView: CommonView {
    fun notifyItemRangeInserted(positionStart: Int, itemCount: Int)
    fun setSummary(manufacturerName: String, mainType: String)
    fun navigateToNextPage(manufacturerId: String, manufacturerName: String, mainType: String, selectedBuiltDate: String)
}