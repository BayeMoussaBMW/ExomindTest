package com.example.exomindtest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exomindtest.R
import com.example.exomindtest.model.UserItem
import com.example.exomindtest.ui.adapter.MainAdapter
import com.example.exomindtest.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.list_fragment.recyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ListResultFragment: Fragment() {
    private lateinit var adapter: MainAdapter
    private var dataList: ArrayList<UserItem> = ArrayList()

    companion object {
        fun newInstance() = ListResultFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Toast.makeText(requireContext(), "ListResultFragment", Toast.LENGTH_SHORT).show()
        setupObservers()
        setupUI()
    }


    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter(dataList)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }


    private fun setupObservers() {
        val searchItem = "Bret"
        viewModel.dataState.observe(requireActivity(), Observer { dataState ->
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


    private fun displayProgressBar(isDisplayed: Boolean){
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun retrieveList(users: List<UserItem>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }

}