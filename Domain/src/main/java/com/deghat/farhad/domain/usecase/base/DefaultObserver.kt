package com.deghat.farhad.domain.usecase.base

import io.reactivex.observers.DisposableObserver

abstract class DefaultObserver<T>: DisposableObserver<T>() {
    override fun onComplete() {
        println("complete")
    }

    override fun onNext(it: T) {

    }

    override fun onError(e: Throwable) {
        if (e.message != null)
            println(e.message)
        else
            println("I don't know what happened! :(")
    }
}