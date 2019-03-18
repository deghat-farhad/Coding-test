package com.deghat.farhad.codingtest.manufacturersList

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.deghat.farhad.codingtest.CommonView
import com.deghat.farhad.codingtest.R
import com.deghat.farhad.data.repository.CarRepositoryImpl
import com.deghat.farhad.domain.model.Manufacturers
import com.deghat.farhad.domain.usecase.GetManufacturers
import com.deghat.farhad.domain.usecase.GetManufacturerParams
import com.deghat.farhad.domain.usecase.base.DefaultObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ActMain : AppCompatActivity(), ManufacturersView {

    private lateinit var recyclerView: RecyclerView
    private val adapter = MyAdapter()
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var progressBar: ProgressBar
    private val presenter = ManufacturersPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        progressBar = findViewById(R.id.progressBar)

        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.my_recycler_view)
        recyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager
        }
        recyclerView.adapter = adapter

        presenter.onCreate()

        /*val manufacturerObserver = object: DefaultObserver<Manufacturers>() {
            override fun onNext(t: Manufacturers) {
                super.onNext(t)
                println(t.wkda)
            }
        }

        val getManufacturerParams = GetManufacturerParams(0, 15)

        GetManufacturers(CarRepositoryImpl(), Schedulers.io(), AndroidSchedulers.mainThread())
                .execute(manufacturerObserver, getManufacturerParams)*/


        /*val mainTypesObserver = object: DefaultObserver<MainTypes>() {
            override fun onNext(t: MainTypes) {
                super.onNext(t)
                println(t.wkda)
            }
        }

        val getMainTypesParams = GetMainTypesParams(107,0, 15)

        GetMainTypes(CarRepositoryImpl(), Schedulers.io(), AndroidSchedulers.mainThread())
                .execute(mainTypesObserver, getMainTypesParams)*/

        /*val builtDatesObserver = object: DefaultObserver<BuiltDates>() {
            override fun onNext(t: BuiltDates) {
                super.onNext(t)
                println(t.wkda)
            }
        }

        val getBuiltDatesParams = GetBuiltDatesParams(107,"Arnage")

        GetBuiltDates(CarRepositoryImpl(), Schedulers.io(), AndroidSchedulers.mainThread())
                .execute(builtDatesObserver, getBuiltDatesParams)*/

    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun showProgress() {
        recyclerView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }

    override fun showConnectionError() {
        Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show()
    }

    override fun hideConnectionError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setItems(Items: Map<String, String>) {
        adapter.setItems(Items)
    }

    override fun updateItems() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToNextPage() {

    }

    override fun showPagination() {

    }

    override fun hidePagination() {

    }

    override fun setSelectedPage() {

    }
}
