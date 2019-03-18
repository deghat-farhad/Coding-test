package com.deghat.farhad.codingtest.mainTypesList

import com.deghat.farhad.codingtest.CommonView
import com.deghat.farhad.codingtest.model.MainTypesItem

interface MainTypesView: CommonView {
    fun showSearchView()
    fun hideSearchView()
    fun setSummary()
    fun setItems(mainTypes: ArrayList<MainTypesItem.MainType>)

}