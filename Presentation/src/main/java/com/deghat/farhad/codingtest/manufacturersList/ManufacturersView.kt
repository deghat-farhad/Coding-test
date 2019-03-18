package com.deghat.farhad.codingtest.manufacturersList

import com.deghat.farhad.codingtest.CommonView

interface ManufacturersView: CommonView {
    fun showPagination()
    fun hidePagination()
    fun setSelectedPage()
}