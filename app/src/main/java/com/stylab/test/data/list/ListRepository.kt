package com.stylab.test.data.list

import android.util.Log
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import com.stylab.test.data.Result
import com.stylab.test.data.list.model.ListModel
import kotlinx.coroutines.*

class ListRepository(private val listRemoteDataSource: ListRemoteDataSource) {

    private val parentJob = Job()
    private val scope = CoroutineScope(Main + parentJob)

    fun loadListData(
        page: Int,
        filter: Boolean = false,
        onSuccess: (List<ListModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        launchLoad(page, filter, onSuccess, onError)
    }

    private fun launchLoad(
        page: Int,
        filter: Boolean = false,
        onSuccess: (List<ListModel>) -> Unit,
        onError: (String) -> Unit
    ) = scope.launch(IO) {
        val result = listRemoteDataSource.loadData(page)
        withContext(Main) {
            when (result) {
                is Result.Success -> {
                    if(filter) onSuccess(filterList(resultList = result.data))
                    else onSuccess(result.data)
                }
                is Result.Error -> onError(result.exception.toString())
            }
        }
    }

    private fun filterList(resultList: List<ListModel>): List<ListModel> {
        val mutableResultList = mutableListOf<ListModel>()
        for ((index, value) in resultList.withIndex()) {
            if ((index+1) % 3 == 0) {

            } else {
                if(index < resultList.size) mutableResultList.add(value)
            }
        }
        return mutableResultList
    }

    fun cancelAllRequests() {
        parentJob.cancelChildren()
    }

    companion object {
        @Volatile
        private var INSTANCE: ListRepository? = null

        fun getInstance(
            remoteDataSource: ListRemoteDataSource
        ): ListRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ListRepository(remoteDataSource)
                    .also { INSTANCE = it }
            }
        }
    }
}