package com.example.exomindtest

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.exomindtest.model.UserItem
import com.example.exomindtest.ui.MainStateEvent
import com.example.exomindtest.ui.MainViewModel
import com.example.exomindtest.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetUserItemEvent)
        //viewModel.setStateEvent(MainStateEvent.GetAlbumItemEvent)
    }


    private fun subscribeObservers(){
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<UserItem>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?){
        if(message != null) text.text = message else text.text = "Unknown error."
    }


    private fun appendBlogTitles(users: List<UserItem>){
        val sb = StringBuilder()
        for(user in users){
            sb.append(user.name + "\n")
            //sb.append(user.username + "\n")
            sb.append(user.email + "\n")
        }
        text.text = sb.toString()
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }


}