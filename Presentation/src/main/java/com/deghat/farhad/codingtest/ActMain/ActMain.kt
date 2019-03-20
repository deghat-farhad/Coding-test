package com.deghat.farhad.codingtest.ActMain

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.deghat.farhad.codingtest.R
import com.deghat.farhad.codingtest.manufacturersList.view.FragManufacturerList

class ActMain : AppCompatActivity(), ActMainView {

    lateinit var presenter: ActMainPresenter
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        presenter = ActMainPresenter(this)
        presenter.initiate(savedInstanceState)
    }

    override fun launchFragment() {
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, FragManufacturerList()
        , getString(R.string.fragManufacturerListTag))
                .commit()
    }
}
