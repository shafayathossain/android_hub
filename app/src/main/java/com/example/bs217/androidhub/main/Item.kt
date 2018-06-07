package com.example.bs217.androidhub.main

import com.google.gson.annotations.SerializedName
import com.orm.SugarRecord
import org.joda.time.DateTime
import org.joda.time.Interval
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

data class Item (
        @SerializedName("id")
    val id : Int,
        @SerializedName("name")
    val name : String,
        @SerializedName("owner")
    var owner : Owner,
        @SerializedName("description")
    val description : String,
        @SerializedName("forks_url")
    val forksUrl : String,
        @SerializedName("branches_url")
    val branchesUrl : String,
        @SerializedName("commits_url")
    val commitsUrl : String,
        @SerializedName("comments_url")
    val commentsUrl : String,
        @SerializedName("issues_url")
    val issuesUrl : String,
        @SerializedName("created_at")
    val createdAt : String,
        @SerializedName("updated_at")
    val updatedAt : String,
        @SerializedName("size")
    val size : Int,
        @SerializedName("forks_count")
    val forksCount : Int,
        @SerializedName("open_issues_count")
    val openIssuesCount : Int,
        @SerializedName("watchers")
    val watchersCount : Int,
        @SerializedName("score")
    val score : Double,
        @SerializedName("stargazers_count")
    val star: Int
) : Serializable{
    fun getUpdateAt(): Pair<Int, Int>{
        var updateDate : String = updatedAt.replace("-", "/")
        updateDate = updateDate.replace("T", " ")
        updateDate = updateDate.substring(0, updateDate.length-2)
        var simpleDateFormat : SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        var date : Date = simpleDateFormat.parse(updateDate)
        var millis : Long = date.time

        var currentDate : Date = simpleDateFormat.parse(simpleDateFormat.format(Date()))

        var interval : Interval = Interval(DateTime(date), DateTime(currentDate))
        var hours : Int = interval.toDuration().standardHours.toInt()
        var day : Int = interval.toDuration().standardDays.toInt()

        if(day != 0){
            return Pair(2, day);
        }
        return Pair(1, hours)
    }

    fun getSize(): String{
        return when(size/1024){
            0 -> size.toString()+"KB"
            else -> (size/1024).toString() + "MB"
        }
    }

    fun getCreatedAtDate() : String{
        var created : String = createdAt.replace("-", "/")
        created = created.substring(0, created.indexOf("T")-1)
        var simpleDateFormat : SimpleDateFormat = SimpleDateFormat("yyyy/mm/dd")
        var date : Date = simpleDateFormat.parse(created);
        var dateFormat : SimpleDateFormat = SimpleDateFormat("MMMM d, yyyy")
        return dateFormat.format(date)
    }
}