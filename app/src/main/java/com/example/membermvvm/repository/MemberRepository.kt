package com.example.membermvvm.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.membermvvm.data.Member
import com.example.membermvvm.utils.DataLoadState

interface MemberRepository {

    fun getPosts(): LiveData<PagedList<Member>>
    fun getDataLoadState(): LiveData<DataLoadState>
}