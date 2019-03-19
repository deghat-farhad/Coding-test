package com.deghat.farhad.codingtest.Summary

import com.deghat.farhad.domain.usecase.GetBuiltDates

interface SummaryView {
    fun setSummary(manufacturerName: String, mainType: String, builtDates: String)
}