package com.example.flowincoroutinekotlin.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowincoroutinekotlin.data.DataRepository
import com.example.flowincoroutinekotlin.data.NetworkResult
import com.example.flowincoroutinekotlin.data.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {

    val responseLiveData: MutableLiveData<NetworkResult<List<Post>>> = MutableLiveData()

    fun getData(){
        viewModelScope.launch {
            responseLiveData.value = NetworkResult.Loading()
            dataRepository.getData().catch { e->
                responseLiveData.value = e.message?.let { NetworkResult.Error(it) }
            }.collect {response ->
                responseLiveData.value = NetworkResult.Success(response)
            }
        }
    }

}