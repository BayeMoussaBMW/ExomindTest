package com.example.exomindtest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exomindtest.model.UserItem
import com.example.exomindtest.retrofit.UserItemNetworkEntity
import com.example.exomindtest.ui.ListResultFragment
import com.example.exomindtest.ui.MainStateEvent
import com.example.exomindtest.ui.MainViewModel
import com.example.exomindtest.ui.adapter.MainAdapter
import com.example.exomindtest.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.setStateEvent(MainStateEvent.GetUserItemEvent)
        setupUI()
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetAlbumItemEvent)

        searchBtn.setOnClickListener{
            Toast.makeText(this, "work in progress", Toast.LENGTH_SHORT).show()
        }

        if (searchBtn != null) {
            searchBtn.setOnClickListener(View.OnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ListResultFragment.newInstance())
                    .commit()
                viewModel.setStateEvent(MainStateEvent.GetUserItemSearchEvent)
                recyclerView.visibility = View.INVISIBLE
                Toast.makeText(this, "work in progress", Toast.LENGTH_SHORT).show()
            })
        }

    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<UserItem>> -> {
                    displayProgressBar(false)
                    recyclerView.visibility = View.VISIBLE
                    displayProgressBar(false)
                    dataState.data?.let { users -> retrieveList(users) }
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    recyclerView.visibility = View.VISIBLE
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                    recyclerView.visibility = View.GONE
                }
            }
        })
    }


    private fun retrieveList(users: List<UserItem>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }





    private fun displayProgressBar(isDisplayed: Boolean){
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onBackPressed() {
        startActivity(Intent(this@MainActivity, MainActivity::class.java))
    }
}