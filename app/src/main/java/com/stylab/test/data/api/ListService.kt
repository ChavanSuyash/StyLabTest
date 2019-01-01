package com.stylab.test.data.api

import com.stylab.test.data.ResponseWrapper
import com.stylab.test.data.list.model.ListModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ListService {

    @GET("test")
    fun getPosts(@Query("pageNo") page: Int): Deferred<ResponseWrapper<List<ListModel>>>

}