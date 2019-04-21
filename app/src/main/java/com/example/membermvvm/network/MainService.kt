package com.example.membermvvm.network

import com.example.membermvvm.data.Members
import com.example.membermvvm.utils.Const
import com.example.membermvvm.utils.Const.API_END
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {

    @GET("members")
    fun getPostsAsync(@Query("page") page:Int): Deferred<Response<Members>>


    companion object {
       operator fun invoke(
           connectivityInterceptor: ConnectivityInterceptor
       ) : MainService {

           val requestInterceptor = Interceptor { chain ->
               val request = chain.request()
                   .newBuilder()
                   .addHeader("Authorization", Const.API_KEY)
                   .build()

               return@Interceptor chain.proceed(request)
           }

           val okHttpClient = OkHttpClient.Builder()
//               .addInterceptor(requestInterceptor)
               .addInterceptor(connectivityInterceptor)
               .build()

           return Retrofit.Builder()
               .baseUrl(API_END)
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(CoroutineCallAdapterFactory())
               .client(okHttpClient)
               .build()
               .create(MainService::class.java)
       }
    }
}