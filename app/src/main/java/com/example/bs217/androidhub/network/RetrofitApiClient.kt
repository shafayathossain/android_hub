package com.example.bs217.androidhub.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiClient{

    private var baseUrl : String = "https://api.github.com/"
    private var gson : Gson = GsonBuilder().setLenient().create()

    private var retrofit : Retrofit = Retrofit.
            Builder().
            baseUrl(baseUrl).
            addConverterFactory(GsonConverterFactory.create(gson)).
            build()

    public fun getClient() : Retrofit{
        return retrofit
    }

}