package com.deghat.farhad.codingtest.manufacturersList

import com.deghat.farhad.data.repository.CarRepositoryImpl
import com.deghat.farhad.domain.model.Manufacturers
import com.deghat.farhad.domain.usecase.GetManufacturers
import com.deghat.farhad.domain.usecase.GetManufacturerParams
import com.deghat.farhad.domain.usecase.base.DefaultObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

const val ITEMS_ON_EACH_PAGE = 15

class ManufacturersPresenter(var manufacturersView: ManufacturersView) {

    private val inProgressUsecases = arrayListOf<GetManufacturers>()
    private val currentPage = 0

    fun onCreate(){
        loadItems(0, ITEMS_ON_EACH_PAGE)
    }

    fun onPageChanged(page: Int){
        loadItems(page, ITEMS_ON_EACH_PAGE)
    }

    fun onPause(){
        for(item in inProgressUsecases)
            item.dispose()
    }

    private fun loadItems(pageNumber: Int, pageSize: Int){

        manufacturersView.showProgress()

        val manufacturerObserver = object: DefaultObserver<Manufacturers>() {

            override fun onNext(it: Manufacturers) {
                super.onNext(it)
                manufacturersView.setItems(it.wkda)
                if(it.totalPageCount > 1) {
                    manufacturersView.showPagination()
                    manufacturersView.setSelectedPage()
                }else{
                    manufacturersView.hidePagination()
                }
            }

            override fun onComplete() {
                super.onComplete()
                manufacturersView.hideProgress()
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                manufacturersView.hideProgress()
                manufacturersView.showConnectionError()
            }
        }

        val getManufacturerParams = GetManufacturerParams(pageNumber, pageSize)

        val getManufacturers = GetManufacturers(CarRepositoryImpl(), Schedulers.io(), AndroidSchedulers.mainThread())

        inProgressUsecases.add(getManufacturers)

        getManufacturers.execute(manufacturerObserver, getManufacturerParams)

    }
}