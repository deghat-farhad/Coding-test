package com.deghat.farhad.codingtest.manufacturersList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.deghat.farhad.codingtest.R
import com.deghat.farhad.codingtest.mainTypesList.FragMainTypesList

import com.deghat.farhad.codingtest.model.ManufacturersItem

const val BUNDLE_SELECTED_MANUFACTURE_KEY = "selectedManufactureId"

class FragManufacturerList: Fragment(), ManufacturersView{

    private lateinit var progressBar: ProgressBar
    private lateinit var errorView: View
    private val presenter = ManufacturersPresenter(this)
    private lateinit var layoutView: View
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private val recyclerAdapter = ManufacturersAdapter(presenter.items,
            presenter::onItemClick,
            presenter::retry)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layoutView = inflater.inflate(R.layout.frag_manufacturer_list, container,
                false)
        initiate()
        return layoutView
    }

    private fun initiate(){
        progressBar = layoutView.findViewById(R.id.progressBar)
        errorView = layoutView.findViewById(R.id.connectionErrorView)
        viewManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL, false)

        setupRecyclerView()
        setOnScrollListener()
        presenter.initiate()
    }

    private fun setupRecyclerView(){
        recyclerView = layoutView.findViewById(R.id.recyclerView)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = recyclerAdapter
        }
    }

    private fun setOnScrollListener(){
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItem = viewManager.findLastVisibleItemPosition()

                if(recyclerAdapter.itemCount - 1 == lastVisibleItem){
                    presenter.loadMore()
                }
            }
        })
    }

    override fun notifyItemRangeInserted(positionStart: Int, itemCount: Int) {
        recyclerAdapter.notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun showProgress() {
        if(recyclerAdapter.itemCount > 0) {
            recyclerAdapter.hideError()
            recyclerAdapter.showProgress()
        }else {
            errorView.visibility = View.GONE
            recyclerView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }

    }

    override fun hideProgress() {
        recyclerView.visibility = View.VISIBLE
        recyclerAdapter.hideProgress()
        progressBar.visibility = View.GONE
    }

    override fun showConnectionError() {
        hideProgress()
        if (recyclerAdapter.itemCount > 0) {
            recyclerAdapter.showError()
            recyclerView.scrollToPosition(recyclerAdapter.itemCount)
        }else{
            recyclerView.visibility = View.GONE
            errorView.visibility = View.VISIBLE
            setRetryClickListener()
        }
    }

    private fun setRetryClickListener() {
        errorView.findViewById<TextView>(R.id.txtViwRetry).setOnClickListener {
            presenter.retry()
        }
    }

    override fun hideConnectionError() {
        recyclerView.visibility = View.VISIBLE
        errorView.visibility = View.GONE
        recyclerAdapter.hideError()
    }

    override fun navigateToNextPage(selectedManufacturer: String) {

        val fragMainTypesList = FragMainTypesList()
        val bundle = Bundle()
        bundle.putString(BUNDLE_SELECTED_MANUFACTURE_KEY, selectedManufacturer)
        fragMainTypesList.arguments = bundle

        fragmentManager?.beginTransaction()
                ?.setCustomAnimations(
                        R.anim.slide_left_enter,
                        R.anim.slide_left_exit,
                        R.anim.slide_right_enter,
                        R.anim.slide_right_exit)
                ?.replace(R.id.frameLayout, fragMainTypesList
                , getString(R.string.fragMainTypesListTag))
        ?.addToBackStack(getString(R.string.fragMainTypesListTag))
        ?.commit()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }
}