package com.example.membermvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.membermvvm.data.Member
import com.example.membermvvm.repository.MemberRepository
import com.example.membermvvm.utils.DataLoadState

class MemberViewModel(private val memberRepository: MemberRepository): ViewModel() {
    fun getPosts(): LiveData<PagedList<Member>> {
        return memberRepository.getPosts()
    }

    fun dataLoadState(): LiveData<DataLoadState> {
        return memberRepository.getDataLoadState()
    }

}