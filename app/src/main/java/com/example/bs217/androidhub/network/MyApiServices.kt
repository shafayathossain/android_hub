package com.example.bs217.androidhub.network

import com.example.bs217.androidhub.main.Search
import com.example.bs217.androidhub.repository_details.Contents
import com.example.bs217.androidhub.repository_details.File

interface MyApiServices {

    fun search(topic:String, page:Int,  callback: ResponseCallback<Search>)
    fun contents(ownersId: String, repositoryName: String, path : String, callback: ResponseCallback<MutableList<Contents>>)
    fun file(ownersId: String, repositoryName: String, fileName : String, callback: ResponseCallback<File>)
}