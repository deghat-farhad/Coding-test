package com.deghat.farhad.codingtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.deghat.farhad.data.remote.Remote
import io.reactivex.schedulers.Schedulers

class ActMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        Remote().getBuiltDates(107, "Arnage")
                .subscribeOn(Schedulers.io())
                .subscribe{ println(it.wkda)}
    }
}