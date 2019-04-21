package com.example.membermvvm.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.membermvvm.data.Member
import com.example.membermvvm.network.MainService
import com.example.membermvvm.network.NoConnectivityException
import com.example.membermvvm.utils.DataLoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import kotlin.coroutines.CoroutineContext

class MemberDataSource(
    private val api:MainService,
    private val dataLoadState: MutableLiveData<DataLoadState>
): PageKeyedDataSource<Int, Member>(),CoroutineScope {

    private val mJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + mJob

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Member>) {
        dataLoadState.postValue(DataLoadState.LOADING)

        launch {
            try {
                val response=api.getPostsAsync(page=1).await()
                when {
                    response.isSuccessful -> {
                        val listing = response.body()
                        val member=listing?.member
                        val list=member?: listOf()
                        Log.d("response",response.body().toString())

                        val meta=response.body()?.meta
                        val page=meta!!.pagination.currentPage
                        callback.onResult(list,0,page+1)
                    }


                }
                dataLoadState.postValue(DataLoadState.LOADED)
            } catch (e: NoConnectivityException) {
                Log.e("MY_ERROR", "No Internet Connection")
                dataLoadState.postValue(DataLoadState.FAILED)
            } catch (e: Throwable) {
                Log.e("MY_ERROR", "I don't know! $e")
                dataLoadState.postValue(DataLoadState.FAILED)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Member>) {
        dataLoadState.postValue(DataLoadState.LOADING)
//        val currentpage=params.key
//        val nextpage=currentpage+1
        launch {
            try {
                val response=api.getPostsAsync(page=params.key).await()
                when {
                    response.isSuccessful -> {
                        val listing = response.body()?.member
                        val meta=response.body()?.meta
                        val page=meta!!.pagination.currentPage
                        callback.onResult(listing?: listOf(),params.key+1 )
                    }
                }
                dataLoadState.postValue(DataLoadState.LOADED)
            } catch (e: NoConnectivityException) {
                Log.e("MY_ERROR", "No Internet Connection")
                dataLoadState.postValue(DataLoadState.FAILED)
            } catch (e: Throwable) {
                Log.e("MY_ERROR", "I don't know! $e")
                dataLoadState.postValue(DataLoadState.FAILED)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Member>) {
        dataLoadState.postValue(DataLoadState.LOADING)

        launch {
            try {
                val response=api.getPostsAsync(page=params.key).await()
                when {
                    response.isSuccessful -> {
                        val listing = response.body()
                        val member=listing?.member
                        val list=member?: listOf()
                        val meta=response.body()?.meta
                        val page=meta!!.pagination.currentPage
                        callback.onResult(list,params.key-1)
                    }
                }
                dataLoadState.postValue(DataLoadState.LOADED)
            } catch (e: NoConnectivityException) {
                Log.e("MY_ERROR", "No Internet Connection")
                dataLoadState.postValue(DataLoadState.FAILED)
            } catch (e: Throwable) {
                Log.e("MY_ERROR", "I don't know! $e")
                dataLoadState.postValue(DataLoadState.FAILED)
            }
        }
    }


}

