package com.deghat.farhad.codingtest.mainTypesList

import com.deghat.farhad.data.repository.CarRepositoryImpl
import com.deghat.farhad.domain.model.MainTypes
import com.deghat.farhad.domain.usecase.GetMainTypes
import com.deghat.farhad.domain.usecase.GetMainTypesParams
import com.deghat.farhad.domain.usecase.base.DefaultObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainTypesPresenter(private val manufacturerId: Int, private val mainTypesView: MainTypesView) {

    private val inProgressUseCases = arrayListOf<GetMainTypes>()
    lateinit var items: Map<String, String>

    fun initiate(){
        loadItems()
    }

    fun destroy(){
        for(inProgressUseCase in inProgressUseCases)
            inProgressUseCase.dispose()
    }

    fun search(input: String){
        val searchResults = items.filterValues { it.contains(input, true) }
        showThese(searchResults)
    }

    private fun loadItems(){

        mainTypesView.hideSearchView()
        mainTypesView.showProgress()

        val mainTypesObserver = object: DefaultObserver<MainTypes>() {

            override fun onNext(it: MainTypes) {
                super.onNext(it)
                items = it.wkda
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

        val getMainTypes = GetMainTypes(CarRepositoryImpl(), Schedulers.io(), AndroidSchedulers.mainThread())
        inProgressUseCases.add(getMainTypes)
        getMainTypes.execute(mainTypesObserver, getMainTypesParams)
    }

    private fun showThese(items: Map<String, String>){
        mainTypesView.setItems(items)
    }
}