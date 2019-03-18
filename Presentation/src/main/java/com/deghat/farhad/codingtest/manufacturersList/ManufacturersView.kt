package com.deghat.farhad.codingtest.manufacturersList

import com.deghat.farhad.codingtest.CommonView
import com.deghat.farhad.codingtest.model.ManufacturersItem

interface ManufacturersView: CommonView {
    fun notifyItemRangeInserted(positionStart: Int, itemCount: Int)
    fun setItems(manufacturers: ArrayList<ManufacturersItem.Manufacturer>)
}