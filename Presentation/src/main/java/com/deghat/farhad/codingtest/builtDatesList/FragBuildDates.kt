package com.deghat.farhad.codingtest.builtDatesList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.deghat.farhad.codingtest.R
import com.deghat.farhad.codingtest.mainTypesList.BUNDLE_SELECTED_MAIN_TYPE_KEY
import com.deghat.farhad.codingtest.manufacturersList.BUNDLE_SELECTED_MANUFACTURE_KEY

class FragBuildDates: Fragment(), BuiltDatesView {

    private lateinit var layoutView: View
    private lateinit var progressBar: ProgressBar
    private lateinit var errorView: View
    private lateinit var presenter: BuiltDatesPresenter
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: BuiltDatesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layoutView = inflater.inflate(R.layout.frag_built_dates, container,
                false)
        setHasOptionsMenu(true)

        val bundle = this.arguments
        if (bundle != null) {
            val selectedManufacturerId = bundle.getString(BUNDLE_SELECTED_MANUFACTURE_KEY, "")
            val selectedMainType = bundle.getString(BUNDLE_SELECTED_MAIN_TYPE_KEY, "")
            presenter = BuiltDatesPresenter(selectedManufacturerId, selectedMainType, this)
            recyclerAdapter = BuiltDatesAdapter(presenter.items, presenter::onItemClick)
        }else{
            throw NullPointerException("selected built date should not be null.")
        }

        initiate()
        return layoutView
    }

    private fun initiate() {
        progressBar = layoutView.findViewById(R.id.progressBar)
        errorView = layoutView.findViewById(R.id.connectionErrorView)
        viewManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL, false)

        setupRecyclerView()
        presenter.initiate()
    }

    private fun setupRecyclerView() {
        recyclerView = layoutView.findViewById(R.id.recyclerView)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = recyclerAdapter
        }
    }

    override fun notifyItemRangeInserted(positionStart: Int, itemCount: Int){
        recyclerAdapter.notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun navigateToNextPage(manufacturerId: String, selectedBuiltDate: String) {

    }

    override fun setSummary() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        errorView.visibility = View.GONE
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun showConnectionError() {
        recyclerView.visibility = View.GONE
        errorView.visibility = View.VISIBLE
        setRetryClickListener()
    }

    private fun setRetryClickListener() {
        errorView.findViewById<TextView>(R.id.txtViwRetry).setOnClickListener {
            presenter.loadItems()
        }
    }

    override fun hideConnectionError() {
        recyclerView.visibility = View.VISIBLE
        errorView.visibility = View.GONE
    }
}