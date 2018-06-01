package com.example.bs217.androidhub.main

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner(
    @SerializedName("login")
    val userName : String,
    @SerializedName("id")
    val id : Int,
    @SerializedName("avatar_url")
    val imageOfOwner : String,
    @SerializedName("url")
    val profileUrl : String,
    @SerializedName("followers_url")
    val followers : String,
    @SerializedName("following_url")
    val followings : String,
    @SerializedName("repos_url")
    val reposOfOwner : String
) : Serializable
