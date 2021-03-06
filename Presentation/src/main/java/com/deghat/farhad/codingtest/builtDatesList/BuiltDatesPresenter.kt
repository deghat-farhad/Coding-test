package com.deghat.farhad.codingtest.builtDatesList

import com.deghat.farhad.codingtest.builtDatesList.view.FragBuildDates
import com.deghat.farhad.codingtest.model.BuiltDatesItem
import com.deghat.farhad.codingtest.model.mapper.BuiltDatesItemMapper
import com.deghat.farhad.data.di.DaggerDataComponent
import com.deghat.farhad.domain.model.BuiltDates
import com.deghat.farhad.domain.usecase.GetBuiltDates
import com.deghat.farhad.domain.usecase.GetBuiltDatesParams
import com.deghat.farhad.domain.usecase.base.DefaultObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BuiltDatesPresenter {

    private lateinit var manufacturerId: String
    private lateinit var manufacturerName: String
    private lateinit var mainType: String
    private lateinit var builtDatesView: BuiltDatesView

    val items = ArrayList<BuiltDatesItem.BuiltDate>()
    private val inProgressUseCases = arrayListOf<GetBuiltDates>()


    fun onItemClick(selectedBuiltDate: String){
        builtDatesView.navigateToNextPage(manufacturerId, manufacturerName, mainType, selectedBuiltDate)
    }

    fun initiate(selectedManufacturerId: String, selectedManufacturerName: String, selectedMainType: String, fragBuildDates: FragBuildDates) {
        manufacturerId = selectedManufacturerId
        manufacturerName = selectedManufacturerName
        mainType = selectedMainType
        builtDatesView = fragBuildDates
        builtDatesView.setSummary(manufacturerName, mainType)
        if(items.isEmpty())
            loadItems()
    }

    fun resume(){
        builtDatesView.setSummary(manufacturerName, mainType)
    }

    fun stop() {
        for (inProgressUseCase in inProgressUseCases)
            inProgressUseCase.dispose()
    }

    fun loadItems() {
        builtDatesView.showProgress()

        val builtDatesObserver = object : DefaultObserver<BuiltDates>() {

            override fun onNext(it: BuiltDates) {
                super.onNext(it)

                val builtDatesItem = BuiltDatesItemMapper().mapToPresentation(it)

                items.clear()
                items.addAll(builtDatesItem.wkda)

                notifyViewer()
            }

            override fun onComplete() {
                super.onComplete()

                builtDatesView.hideProgress()
            }

            override fun onError(e: Throwable) {
                super.onError(e)

                builtDatesView.hideProgress()
                builtDatesView.showConnectionError()
            }
        }

        val getBuiltDatesParams = GetBuiltDatesParams(manufacturerId, mainType)

        val getBuiltDates = GetBuiltDates(DaggerDataComponent.builder().build().getCarRepositoryImpl(),
                Schedulers.io(), AndroidSchedulers.mainThread())

        inProgressUseCases.add(getBuiltDates)

        getBuiltDates.execute(builtDatesObserver, getBuiltDatesParams)
    }

    private fun notifyViewer() {
        builtDatesView.notifyItemRangeInserted(0, items.size)
    }
}