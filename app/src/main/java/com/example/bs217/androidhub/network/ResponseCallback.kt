package com.example.bs217.androidhub.network

import com.example.bs217.androidhub.main.Search

interface ResponseCallback<T> {
    fun onSuccess(data: T)
    fun onError(th:Throwable)
}