package com.errorizers.domain.usecase.base

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver


abstract class UseCase<T, Params>(private val executorThread: Scheduler,
                                  private val uiThread: Scheduler) {
    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params){
        val observable = buildUseCaseObservable(params)
                .subscribeOn(executorThread)
                .observeOn(uiThread)
        addDisposable(observable.subscribeWith(observer))
    }

    private fun addDisposable(disposable: Disposable){
        disposables.add(disposable)
    }

    fun dispose(){
        if(!disposables.isDisposed)
            disposables.dispose()
    }
}