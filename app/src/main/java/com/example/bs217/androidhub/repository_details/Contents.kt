package com.example.bs217.androidhub.repository_details

import com.google.gson.annotations.SerializedName

class Contents {
    @SerializedName("name")
    lateinit var name: String
    @SerializedName("path")
    lateinit var path: String
    @SerializedName("size")
    var size: Int = 0
    @SerializedName("url")
    lateinit var url: String
    @SerializedName("type")
    lateinit var type: String


}
