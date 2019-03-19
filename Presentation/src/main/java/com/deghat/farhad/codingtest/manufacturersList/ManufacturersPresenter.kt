package com.deghat.farhad.codingtest.manufacturersList

import com.deghat.farhad.codingtest.model.ManufacturersItem
import com.deghat.farhad.codingtest.model.mapper.ManufacturersItemMapper
import com.deghat.farhad.data.repository.CarRepositoryImpl
import com.deghat.farhad.domain.model.Manufacturers
import com.deghat.farhad.domain.usecase.GetManufacturers
import com.deghat.farhad.domain.usecase.GetManufacturerParams
import com.deghat.farhad.domain.usecase.base.DefaultObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

const val ITEMS_ON_EACH_PAGE = 15

class ManufacturersPresenter(var manufacturersView: ManufacturersView) {

    private val inProgressUseCases = arrayListOf<GetManufacturers>()
    private var nextPage = 0
    private var totalPages = 0
    private var isFirstLoad = true
    private var isLoading = false
    private var isNeededToLoadMore = false
    private var isUnableToLoad = false
    val items = ArrayList<ManufacturersItem.Manufacturer>()

    fun initiate(){
        loadMore()
    }

    fun loadMore(){
        if(!isLoading && !isUnableToLoad) {
            if (isFirstLoad || (nextPage < totalPages))
                loadItems(nextPage, ITEMS_ON_EACH_PAGE)
        }
    }

    fun stop(){
        for(inProgressUseCase in inProgressUseCases)
            inProgressUseCase.dispose()
        if(isLoading){
            isLoading = false
            isNeededToLoadMore = true
            manufacturersView.hideProgress()
        }
    }

    fun resume(){
        manufacturersView.resetTitle()
        if(isNeededToLoadMore) {
            loadMore()
            isNeededToLoadMore = false
        }
    }

    fun retry(){
        isUnableToLoad = false
        loadMore()
    }

    fun onItemClick(selectedManufacturerId: String, selectedManufacturerName: String) {
        manufacturersView.navigateToNextPage(selectedManufacturerId, selectedManufacturerName)
    }

    private fun loadItems(pageNumber: Int, pageSize: Int) {

        val manufacturerObserver = object : DefaultObserver<Manufacturers>() {

            override fun onNext(it: Manufacturers) {
                super.onNext(it)

                val manufacturersItem = ManufacturersItemMapper().mapToPresentation(it)

                val lastItemPosition = items.size
                val newItemsCount = manufacturersItem.wkda.size
                items.addAll(manufacturersItem.wkda)

                manufacturersView.notifyItemRangeInserted(lastItemPosition, newItemsCount)

                nextPage = manufacturersItem.page + 1
                totalPages = manufacturersItem.totalPageCount
            }

            override fun onComplete() {
                super.onComplete()

                manufacturersView.hideProgress()
                isLoading = false

                if (isFirstLoad)
                    isFirstLoad = false
            }

            override fun onError(e: Throwable) {
                super.onError(e)

                manufacturersView.hideProgress()
                manufacturersView.showConnectionError()
                isLoading = false
                isUnableToLoad = true
            }
        }

        val getManufacturerParams = GetManufacturerParams(pageNumber, pageSize)

        val getManufacturers = GetManufacturers(CarRepositoryImpl(),
                Schedulers.io(), AndroidSchedulers.mainThread())

        inProgressUseCases.add(getManufacturers)

        isLoading = true
        manufacturersView.showProgress()
        getManufacturers.execute(manufacturerObserver, getManufacturerParams)

    }
}