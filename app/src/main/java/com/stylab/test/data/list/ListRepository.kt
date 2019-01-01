package com.stylab.test.data.list

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import com.stylab.test.data.Result
import com.stylab.test.data.list.model.ListModel
import kotlinx.coroutines.*

class ListRepository(private val listRemoteDataSource: ListRemoteDataSource) {

    private val parentJob = Job()
    private val scope = CoroutineScope(Main + parentJob)

    fun loadListData(
        page : Int,
        onSuccess : (List<ListModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        launchLoad(page, onSuccess, onError)
    }

    private fun launchLoad(
        page: Int,
        onSuccess: (List<ListModel>) -> Unit,
        onError: (String) -> Unit
    ) = scope.launch(IO) {
        val result = listRemoteDataSource.loadData(page)
        withContext(Main) {
            when (result) {
                is Result.Success -> onSuccess(result.data)
                is Result.Error -> onError(result.exception.toString())
            }
        }
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