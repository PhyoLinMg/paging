package com.example.membermvvm.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.membermvvm.data.Member
import com.example.membermvvm.data.Members
import com.example.membermvvm.network.MainService
import com.example.membermvvm.utils.DataLoadState

class MemberDataSourceFactory(val api: MainService, val dataLoadState: MutableLiveData<DataLoadState>) : DataSource.Factory<Int, Member>()  {
    override fun create(): DataSource<Int, Member> {
        return MemberDataSource(api,dataLoadState)
    }
}