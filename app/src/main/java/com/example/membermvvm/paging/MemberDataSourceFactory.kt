package com.example.membermvvm.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.membermvvm.data.Data
import com.example.membermvvm.network.MainService
import com.example.membermvvm.utils.DataLoadState

class MemberDataSourceFactory(val api: MainService, val dataLoadState: MutableLiveData<DataLoadState>) : DataSource.Factory<Int, Data>()  {
    override fun create(): DataSource<Int, Data> {
        return MemberDataSource(api,dataLoadState)
    }
}