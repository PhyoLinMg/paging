package com.example.membermvvm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.membermvvm.adapter.MemberAdapter
import com.example.membermvvm.data.Data
import com.example.membermvvm.network.ConnectivityInterceptorImpl
import com.example.membermvvm.network.MainService
import com.example.membermvvm.repository.MemberRepositoryImpl
import com.example.membermvvm.utils.DataLoadState
import com.example.membermvvm.viewmodel.MemberViewModel
import com.example.membermvvm.viewmodel.MemberViewModelFactory

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.lang.reflect.Member

class MainActivity : AppCompatActivity(),LifecycleOwner,KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: MemberViewModelFactory by instance()
    lateinit var memberAdapter: MemberAdapter
    lateinit var api: MainService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        memberAdapter= MemberAdapter(this)
        list.layoutManager = LinearLayoutManager(this)
        list.setHasFixedSize(true)
        list.adapter = memberAdapter
        api = MainService(ConnectivityInterceptorImpl(this))

        val viewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(MemberViewModel::class.java)




        viewModel.getPosts().observe(this, Observer<PagedList<Data>> { pagedList ->
            Log.d("gg",pagedList.toString())
            memberAdapter.submitList(pagedList)
            memberAdapter.notifyDataSetChanged()
        })

        viewModel.dataLoadState().observe(this, Observer {
            when(it) {
                DataLoadState.LOADING -> {
                    determinateBar.visibility = View.VISIBLE
                }
                DataLoadState.LOADED -> {
                    determinateBar.visibility = View.GONE
                }
                DataLoadState.FAILED -> {
                    determinateBar.visibility = View.VISIBLE
                }
            }
        })
        fab.setOnClickListener { view ->

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
