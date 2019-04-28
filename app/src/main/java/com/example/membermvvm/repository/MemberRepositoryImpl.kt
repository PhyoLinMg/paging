package com.example.membermvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.membermvvm.data.Data
import com.example.membermvvm.network.MainService
import com.example.membermvvm.paging.MemberDataSourceFactory
import com.example.membermvvm.utils.DataLoadState

class MemberRepositoryImpl(val api: MainService, val dataLoadState: MutableLiveData<DataLoadState> = MutableLiveData()): MemberRepository {
    private lateinit var memberDataSourceFactory: MemberDataSourceFactory
    override fun getPosts(): LiveData<PagedList<Data>> {
        memberDataSourceFactory = MemberDataSourceFactory(api, dataLoadState)
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        val liveData = LivePagedListBuilder(memberDataSourceFactory, config).build()
        return liveData

    }

    override fun getDataLoadState(): LiveData<DataLoadState> {
        return memberDataSourceFactory.dataLoadState
    }
}