package com.example.bs217.androidhub.main

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Search(

    @SerializedName("total_count")
    var totalCount : Int,
    @SerializedName("items")
    var items : MutableList<Item>

) : Serializable
