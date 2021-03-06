package com.deghat.farhad.codingtest.manufacturersList.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.deghat.farhad.codingtest.R


class ManufacturerHolder(holderView: View): RecyclerView.ViewHolder(holderView) {
    val txtViewManufacturer: TextView = holderView.findViewById(R.id.txtViwManufacturer)
    val manufacturerHolderRoot: LinearLayout = holderView.findViewById(R.id.holderRoot)
}
