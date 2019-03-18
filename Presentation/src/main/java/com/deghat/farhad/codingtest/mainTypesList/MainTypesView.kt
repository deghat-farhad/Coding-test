package com.deghat.farhad.codingtest.mainTypesList

import com.deghat.farhad.codingtest.CommonView

interface MainTypesView: CommonView {
    fun showSearchView()
    fun hideSearchView()
    fun setSummary()
}