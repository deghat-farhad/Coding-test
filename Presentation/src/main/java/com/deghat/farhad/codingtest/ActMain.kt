package com.deghat.farhad.codingtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

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
}
