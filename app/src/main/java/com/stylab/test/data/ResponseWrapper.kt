package com.stylab.test.data

data class ResponseWrapper<out T : Any>(
    val success : Boolean,
    val data :  T?,
    val errors : List<String>
)