package com.deghat.farhad.codingtest.manufacturersList

import com.deghat.farhad.codingtest.CommonView

interface ManufacturersView: CommonView {
    fun notifyItemRangeInserted(positionStart: Int, itemCount: Int)
    fun navigateToNextPage(selectedManufacturerId: String, selectedManufacturerName: String)
}