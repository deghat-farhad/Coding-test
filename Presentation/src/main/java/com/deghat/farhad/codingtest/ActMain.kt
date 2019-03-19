package com.deghat.farhad.codingtest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.deghat.farhad.codingtest.manufacturersList.FragManufacturerList

class ActMain : AppCompatActivity(), ActMainView {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        launchFragment()
    }

    override fun launchFragment() {
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, FragManufacturerList()
        , getString(R.string.fragManufacturerListTag)).commit()
    }
}
