package com.deghat.farhad.codingtest.builtDatesList

import com.deghat.farhad.codingtest.CommonView

interface BuiltDatesView: CommonView {
    fun notifyItemRangeInserted(positionStart: Int, itemCount: Int)
    fun setSummary()
    fun navigateToNextPage(manufacturerId: String, selectedBuiltDate: String)
}