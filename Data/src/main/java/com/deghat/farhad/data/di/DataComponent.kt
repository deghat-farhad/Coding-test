package com.deghat.farhad.data.di

import com.deghat.farhad.data.repository.CarRepositoryImpl
import dagger.Component

@Component(modules = [CarRepositoryImplModule::class])
interface DataComponent {
    fun getCarRepositoryImpl(): CarRepositoryImpl
}