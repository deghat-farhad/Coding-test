package com.deghat.farhad.codingtest.Summary

import com.deghat.farhad.domain.usecase.GetBuiltDates

class SummaryPresenter(private val manufacturer: String,
                       private val mainType: String,
                       private val builtDate: String,
                       private val summaryView: SummaryView) {
    fun initiate(){
        summaryView.setSummary(manufacturer, mainType, builtDate)
    }
}