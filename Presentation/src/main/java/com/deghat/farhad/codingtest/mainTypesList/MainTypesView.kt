package com.deghat.farhad.codingtest.mainTypesList

import com.deghat.farhad.codingtest.CommonView
import com.deghat.farhad.codingtest.model.MainTypesItem

interface MainTypesView: CommonView {
    fun showSearchView()
    fun hideSearchView()
    fun setSummary(manufacturerName: String)
    fun setItems(mainTypes: ArrayList<MainTypesItem.MainType>)
    fun navigateToNextPage(manufacturerId: String, manufacturerName: String, selectedMainType: String)

}