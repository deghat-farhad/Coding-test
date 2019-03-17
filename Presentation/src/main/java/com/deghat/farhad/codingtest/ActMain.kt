package com.deghat.farhad.codingtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.deghat.farhad.data.remote.Remote
import com.deghat.farhad.data.repository.CarRepositoryImpl
import com.deghat.farhad.domain.model.Manufacturers
import com.deghat.farhad.domain.usecase.GetManufacturer
import com.deghat.farhad.domain.usecase.GetManufacturerParams
import com.errorizers.domain.usecase.base.DefaultObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ActMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)


        val manufacturerObserver = object: DefaultObserver<Manufacturers>() {
            override fun onNext(t: Manufacturers) {
                super.onNext(t)
                println(t.wkda)
            }
        }

        val getManufacturerParams = GetManufacturerParams(0, 15)

        GetManufacturer(CarRepositoryImpl(), Schedulers.io(), AndroidSchedulers.mainThread())
                .execute(manufacturerObserver, getManufacturerParams)
    }
}
