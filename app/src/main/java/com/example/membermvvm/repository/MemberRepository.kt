package com.example.membermvvm.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.membermvvm.data.Data
import com.example.membermvvm.utils.DataLoadState

interface MemberRepository {

    fun getPosts(): LiveData<PagedList<Data>>
    fun getDataLoadState(): LiveData<DataLoadState>
}