package com.example.exomindtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exomindtest.model.UserItem
import com.example.exomindtest.repository.MainRepository
import com.example.exomindtest.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val mainRepository: MainRepository,
): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<UserItem>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<UserItem>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetUserItemEvent -> {
                    mainRepository.getUserItem()
                        .onEach {dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                MainStateEvent.None -> {
                    // who cares
                }
            }
        }
    }

}


sealed class MainStateEvent{

    object GetUserItemEvent: MainStateEvent()

    object None: MainStateEvent()
}
