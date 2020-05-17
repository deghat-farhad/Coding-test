package com.deghat.farhad.codingtest.mainTypesList.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.deghat.farhad.codingtest.R
import com.deghat.farhad.codingtest.builtDatesList.view.FragBuildDates
import com.deghat.farhad.codingtest.mainTypesList.MainTypesPresenter
import com.deghat.farhad.codingtest.mainTypesList.MainTypesView
import com.deghat.farhad.codingtest.model.MainTypesItem
import com.deghat.farhad.codingtest.manufacturersList.view.BUNDLE_SELECTED_MANUFACTURE_KEY
import com.deghat.farhad.codingtest.manufacturersList.view.BUNDLE_SELECTED_MANUFACTURE_NAME
import kotlinx.android.synthetic.main.act_main.*

const val BUNDLE_SELECTED_MAIN_TYPE_KEY = "selectedMainType"
class FragMainTypesList: Fragment(), MainTypesView {

    private lateinit var layoutView: View
    private lateinit var progressBar: ProgressBar
    private lateinit var errorView: View
    private val presenter = MainTypesPresenter()
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: MainTypeseAdapter
    private var searchItem: MenuItem? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layoutView = inflater.inflate(R.layout.frag_main_types_list, container,
                false)
        setHasOptionsMenu(true)

        val bundle = this.arguments
        val selectedManufacturerId: String
        val selectedManufacturerName: String
        if (bundle != null) {
            selectedManufacturerId = bundle.getString(BUNDLE_SELECTED_MANUFACTURE_KEY, "")
            selectedManufacturerName = bundle.getString(BUNDLE_SELECTED_MANUFACTURE_NAME, "")

            recyclerAdapter = MainTypeseAdapter(presenter.items, presenter::onItemClick)
        }else{
            throw IllegalArgumentException("selected manufacture id should not be null.")
        }

        initiate(selectedManufacturerId, selectedManufacturerName)
        return layoutView
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity?.menuInflater?.inflate(R.menu.search_menu, menu)
        searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = context?.resources?.getString(R.string.search)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(searchString: String?): Boolean {
                if (searchString != null) {
                    presenter.search(searchString)
                }
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    private fun initiate(selectedManufacturerId: String, selectedManufacturerName: String) {
        progressBar = layoutView.findViewById(R.id.progressBar)
        errorView = layoutView.findViewById(R.id.connectionErrorView)
        viewManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL, false)
        progressBar.visibility = View.GONE

        setupRecyclerView()
        presenter.initiate(selectedManufacturerId, selectedManufacturerName, this)
    }

    private fun setupRecyclerView() {
        recyclerView = layoutView.findViewById(R.id.recyclerView)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = recyclerAdapter
        }
    }

    override fun navigateToNextPage(manufacturerId: String, manufacturerName: String, selectedMainType: String) {
        val fragBuildDates = FragBuildDates()
        val bundle = Bundle()
        bundle.putString(BUNDLE_SELECTED_MANUFACTURE_KEY, manufacturerId)
        bundle.putString(BUNDLE_SELECTED_MANUFACTURE_NAME, manufacturerName)
        bundle.putString(BUNDLE_SELECTED_MAIN_TYPE_KEY, selectedMainType)
        fragBuildDates.arguments = bundle

        fragmentManager?.beginTransaction()
                ?.setCustomAnimations(
                        R.anim.slide_left_enter,
                        R.anim.slide_left_exit,
                        R.anim.slide_right_enter,
                        R.anim.slide_right_exit)
                ?.replace(R.id.frameLayout, fragBuildDates
                        , getString(R.string.fragBuiltDatesTag))
                ?.addToBackStack(getString(R.string.fragBuiltDatesTag))
                ?.commit()
    }

    override fun showSearchView() {
        searchItem?.isVisible = true
    }

    override fun hideSearchView() {
        searchItem?.isVisible = false
    }

    override fun setSummary(manufacturerName: String) {
        activity?.toolbar?.title = manufacturerName
    }

    override fun setItems(mainTypes: ArrayList<MainTypesItem.MainType>) {
        recyclerAdapter.setItems(mainTypes)
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