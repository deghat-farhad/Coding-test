package com.deghat.farhad.codingtest

import com.deghat.farhad.codingtest.model.ManufacturersItem

interface CommonView {
    fun showProgress()
    fun hideProgress()
    fun showConnectionError()
    fun hideConnectionError()
    fun updateItems()
    fun navigateToNextPage()
}