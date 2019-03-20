package com.deghat.farhad.codingtest.ActMain

import android.os.Bundle

class ActMainPresenter(private val actMainView: ActMainView) {
    fun initiate(savedInstanceState: Bundle?){
        if(savedInstanceState == null)
            actMainView.launchFragment()
    }
}