package com.example.bs217.androidhub.repository_details

import android.util.Base64
import com.google.gson.annotations.SerializedName

class File {

    @SerializedName("content")
    var content : String = ""

    @SerializedName("html_url")
    lateinit var htmlUrl :String

    @SerializedName("download_url")
    lateinit var downloadUrl: String

    fun getContentString() : String {
//        content = content.replace("\n", "")
        var decodeValue : ByteArray
        decodeValue = Base64.decode(content, Base64.DEFAULT)
        return String(decodeValue)
    }
}