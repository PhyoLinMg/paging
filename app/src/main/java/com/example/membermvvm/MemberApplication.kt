package com.example.membermvvm

import android.app.Application
import com.example.membermvvm.network.ConnectivityInterceptor
import com.example.membermvvm.network.ConnectivityInterceptorImpl
import com.example.membermvvm.network.MainService
import com.example.membermvvm.paging.MemberDataSource
import com.example.membermvvm.repository.MemberRepository
import com.example.membermvvm.repository.MemberRepositoryImpl
import com.example.membermvvm.viewmodel.MemberViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MemberApplication: Application() ,KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MemberApplication))


        bind() from singleton { MainService(instance()) }
        bind<ConnectivityInterceptor>() with singleton {
            ConnectivityInterceptorImpl(instance()) }
        bind() from provider { MemberViewModelFactory(instance()) }
        bind<MemberRepository>() with singleton { MemberRepositoryImpl(instance())}


    }
    override fun onCreate() {
        super.onCreate()

    }
}