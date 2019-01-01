package com.stylab.test.data.list

import com.stylab.test.data.Result
import com.stylab.test.data.api.ListService
import com.stylab.test.data.list.model.ListModel
import com.stylab.test.data.saferApiCall
import java.io.IOException

class ListRemoteDataSource(private val service: ListService) {

    suspend fun loadData(page : Int) : Result<List<ListModel>> = saferApiCall(
        call = { requestData(page) },
        errorMessage = "Error loading list data"
    )

    private suspend fun requestData(page : Int) : Result<List<ListModel>>{
        val dataResponse = service.getPosts(page).await()
        if(dataResponse.success) {
            val data = dataResponse.data
            if(data != null)
                return Result.Success(data)
        }
        return Result.Error(
            IOException("Error loading list data " + "${dataResponse.errors}}")
        )
    }

}