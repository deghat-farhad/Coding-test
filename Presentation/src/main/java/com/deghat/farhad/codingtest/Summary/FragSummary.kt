package com.deghat.farhad.codingtest.Summary

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.deghat.farhad.codingtest.R
import com.deghat.farhad.codingtest.builtDatesList.view.BUNDLE_SELECTED_BUILT_DATE_KEY
import com.deghat.farhad.codingtest.mainTypesList.view.BUNDLE_SELECTED_MAIN_TYPE_KEY
import com.deghat.farhad.codingtest.manufacturersList.view.BUNDLE_SELECTED_MANUFACTURE_NAME
import kotlinx.android.synthetic.main.act_main.*

class FragSummary: Fragment(), SummaryView {

    private lateinit var layoutView: View
    private lateinit var txtViwManufacturer: TextView
    private lateinit var txtViwMainType: TextView
    private lateinit var txtViwBuiltDate: TextView
    private lateinit var presenter: SummaryPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layoutView = inflater.inflate(R.layout.frag_summary, container,
                false)

        val bundle = this.arguments
        if (bundle != null) {
            val selectedManufacturerId = bundle.getString(BUNDLE_SELECTED_MANUFACTURE_NAME, "")
            val selectedMainType = bundle.getString(BUNDLE_SELECTED_MAIN_TYPE_KEY, "")
            val selectedBuiltDate = bundle.getString(BUNDLE_SELECTED_BUILT_DATE_KEY, "")
            presenter = SummaryPresenter(selectedManufacturerId, selectedMainType, selectedBuiltDate, this)
        }else{
            throw NullPointerException("selected built date should not be null.")
        }

        initiate()
        presenter.initiate()
        return layoutView
    }

    private fun initiate() {
        txtViwManufacturer = layoutView.findViewById(R.id.TxtViwManufacturer)
        txtViwMainType = layoutView.findViewById(R.id.txtViwMainType)
        txtViwBuiltDate = layoutView.findViewById(R.id.txtViwBuiltDate)
        activity?.toolbar?.title = activity?.resources?.getString(R.string.summary)
    }

    override fun setSummary(manufacturerName: String, mainType: String, builtDates: String) {
        txtViwManufacturer.text = manufacturerName
        txtViwMainType.text = mainType
        txtViwBuiltDate.text = builtDates
    }
}