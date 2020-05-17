package com.deghat.farhad.codingtest.mainTypesList

import com.deghat.farhad.codingtest.model.MainTypesItem
import com.deghat.farhad.codingtest.model.mapper.MainTypesItemMapper
import com.deghat.farhad.data.di.DaggerDataComponent
import com.deghat.farhad.domain.model.MainTypes
import com.deghat.farhad.domain.usecase.GetMainTypes
import com.deghat.farhad.domain.usecase.GetMainTypesParams
import com.deghat.farhad.domain.usecase.base.DefaultObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainTypesPresenter {

    private lateinit var manufacturerId: String
    private lateinit var manufacturerName: String
    private lateinit var mainTypesView: MainTypesView

    private val inProgressUseCases = arrayListOf<GetMainTypes>()
    val items = ArrayList<MainTypesItem.MainType>()

    fun initiate(selectedManufacturerId: String, selectedManufacturerName: String, mainTypesView: MainTypesView) {
        this.mainTypesView = mainTypesView
        manufacturerId = selectedManufacturerId
        manufacturerName = selectedManufacturerName
        mainTypesView.setSummary(manufacturerName)
        mainTypesView.setItems(items)
        if(items.isEmpty())
            loadItems()
    }

    fun resume(){
        mainTypesView.setSummary(manufacturerName)
    }

    fun stop() {
        for (inProgressUseCase in inProgressUseCases)
            inProgressUseCase.dispose()
    }

    fun search(input: String) {
        val searchResults = items.filter { it.value.contains(input, true) }
        showThese(ArrayList(searchResults))
    }

    fun onItemClick (selectedMainType: String){
        mainTypesView.navigateToNextPage(manufacturerId, manufacturerName, selectedMainType)
    }

    fun loadItems() {

        mainTypesView.hideSearchView()
        mainTypesView.showProgress()

        val mainTypesObserver = object : DefaultObserver<MainTypes>() {

            override fun onNext(it: MainTypes) {
                super.onNext(it)

                val mainTypesItem = MainTypesItemMapper().mapToPresentation(it)

                items.clear()
                items.addAll(mainTypesItem.wkda)

                showThese(items)
            }

            override fun onComplete() {
                super.onComplete()

                mainTypesView.hideProgress()
                mainTypesView.showSearchView()
            }

            override fun onError(e: Throwable) {
                super.onError(e)

                mainTypesView.hideProgress()
                mainTypesView.showConnectionError()
            }
        }

        val getMainTypesParams = GetMainTypesParams(manufacturerId)

        val getMainTypes = GetMainTypes(DaggerDataComponent.builder().build().getCarRepositoryImpl(),
                Schedulers.io(), AndroidSchedulers.mainThread())

        inProgressUseCases.add(getMainTypes)

        getMainTypes.execute(mainTypesObserver, getMainTypesParams)
    }

    private fun showThese(items: ArrayList<MainTypesItem.MainType>) {
        mainTypesView.setItems(items)
    }

}