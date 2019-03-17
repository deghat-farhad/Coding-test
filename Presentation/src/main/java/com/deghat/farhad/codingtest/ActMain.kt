package com.deghat.farhad.codingtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.deghat.farhad.data.repository.CarRepositoryImpl
import com.deghat.farhad.domain.model.MainTypes
import com.deghat.farhad.domain.model.Manufacturers
import com.deghat.farhad.domain.usecase.GetMainTypes
import com.deghat.farhad.domain.usecase.GetMainTypesParams
import com.deghat.farhad.domain.usecase.GetManufacturer
import com.deghat.farhad.domain.usecase.GetManufacturerParams
import com.errorizers.domain.usecase.base.DefaultObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ActMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)


        /*val manufacturerObserver = object: DefaultObserver<Manufacturers>() {
            override fun onNext(t: Manufacturers) {
                super.onNext(t)
                println(t.wkda)
            }
        }

        val getManufacturerParams = GetManufacturerParams(0, 15)

        GetManufacturer(CarRepositoryImpl(), Schedulers.io(), AndroidSchedulers.mainThread())
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
    }
}
