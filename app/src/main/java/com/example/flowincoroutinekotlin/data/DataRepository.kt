package com.example.flowincoroutinekotlin.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataRepository @Inject constructor(private val api: Api) {

    suspend fun getData(): Flow<List<Post>> = flow {
        val response = api.getData()
        emit(response)
    }.flowOn(Dispatchers.IO)

}