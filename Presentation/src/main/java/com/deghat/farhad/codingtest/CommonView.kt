package com.deghat.farhad.codingtest

interface CommonView {
    fun showProgress()
    fun hideProgress()
    fun showConnectionError()
    fun hideConnectionError()
    fun setItems(Items: Map<String, String>)
    fun updateItems()
    fun navigateToNextPage()
}